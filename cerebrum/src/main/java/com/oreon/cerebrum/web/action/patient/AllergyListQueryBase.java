package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Allergy;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.patient.Allergy;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AllergyListQueryBase extends BaseQuery<Allergy, Long> {

	private static final String EJBQL = "select allergy from Allergy allergy";

	protected Allergy allergy = new Allergy();

	@In(create = true)
	AllergyAction allergyAction;

	public AllergyListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Allergy getAllergy() {
		return allergy;
	}

	@Override
	public Allergy getInstance() {
		return getAllergy();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('allergy', 'view')}")
	public List<Allergy> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Allergy> getEntityClass() {
		return Allergy.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"allergy.id = #{allergyList.allergy.id}",

			"allergy.archived = #{allergyList.allergy.archived}",

			"allergy.patient.id = #{allergyList.allergy.patient.id}",

			"allergy.allergen.id = #{allergyList.allergy.allergen.id}",

			"allergy.severity = #{allergyList.allergy.severity}",

			"allergy.dateCreated <= #{allergyList.dateCreatedRange.end}",
			"allergy.dateCreated >= #{allergyList.dateCreatedRange.begin}",};

	public List<Allergy> getAllergysByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		allergy.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedAllergy")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (allergy.getPatient() == null) {
			allergy.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		allergy.getPatient().setId(id);
	}

	public Long getPatientId() {
		return allergy.getPatient() == null ? null : allergy.getPatient()
				.getId();
	}

	public void setAllergenId(Long id) {
		if (allergy.getAllergen() == null) {
			allergy.setAllergen(new com.oreon.cerebrum.patient.Allergen());
		}
		allergy.getAllergen().setId(id);
	}

	public Long getAllergenId() {
		return allergy.getAllergen() == null ? null : allergy.getAllergen()
				.getId();
	}

	//@Restrict("#{s:hasPermission('allergy', 'delete')}")
	public void archiveById(Long id) {
		allergyAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Allergy e) {

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAllergen() != null ? e.getAllergen().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getSeverity() != null ? e.getSeverity() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Patient" + ",");

		builder.append("Allergen" + ",");

		builder.append("Severity" + ",");

		builder.append("\r\n");
	}
}
