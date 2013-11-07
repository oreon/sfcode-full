package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Immunization;

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

import com.oreon.cerebrum.patient.Immunization;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ImmunizationListQueryBase
		extends
			BaseQuery<Immunization, Long> {

	private static final String EJBQL = "select immunization from Immunization immunization";

	protected Immunization immunization = new Immunization();

	@In(create = true)
	ImmunizationAction immunizationAction;

	public ImmunizationListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Immunization getImmunization() {
		return immunization;
	}

	@Override
	public Immunization getInstance() {
		return getImmunization();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('immunization', 'view')}")
	public List<Immunization> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Immunization> getEntityClass() {
		return Immunization.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateRange = new Range<Date>();

	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"immunization.id = #{immunizationList.immunization.id}",

			"immunization.archived = #{immunizationList.immunization.archived}",

			"immunization.date >= #{immunizationList.dateRange.begin}",
			"immunization.date <= #{immunizationList.dateRange.end}",

			"immunization.patient.id = #{immunizationList.immunization.patient.id}",

			"immunization.vaccine.id = #{immunizationList.immunization.vaccine.id}",

			"immunization.dateCreated <= #{immunizationList.dateCreatedRange.end}",
			"immunization.dateCreated >= #{immunizationList.dateCreatedRange.begin}",};

	public List<Immunization> getImmunizationsByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		immunization.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedImmunization")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (immunization.getPatient() == null) {
			immunization.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		immunization.getPatient().setId(id);
	}

	public Long getPatientId() {
		return immunization.getPatient() == null ? null : immunization
				.getPatient().getId();
	}

	public void setVaccineId(Long id) {
		if (immunization.getVaccine() == null) {
			immunization.setVaccine(new com.oreon.cerebrum.patient.Vaccine());
		}
		immunization.getVaccine().setId(id);
	}

	public Long getVaccineId() {
		return immunization.getVaccine() == null ? null : immunization
				.getVaccine().getId();
	}

	//@Restrict("#{s:hasPermission('immunization', 'delete')}")
	public void archiveById(Long id) {
		immunizationAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Immunization e) {

		builder.append("\"" + (e.getDate() != null ? e.getDate() : "") + "\",");

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getVaccine() != null ? e.getVaccine().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Date" + ",");

		builder.append("Patient" + ",");

		builder.append("Vaccine" + ",");

		builder.append("\r\n");
	}
}
