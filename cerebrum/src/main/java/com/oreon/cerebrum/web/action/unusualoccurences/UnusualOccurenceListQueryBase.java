package com.oreon.cerebrum.web.action.unusualoccurences;

import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;

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

import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class UnusualOccurenceListQueryBase
		extends
			BaseQuery<UnusualOccurence, Long> {

	private static final String EJBQL = "select unusualOccurence from UnusualOccurence unusualOccurence";

	protected UnusualOccurence unusualOccurence = new UnusualOccurence();

	@In(create = true)
	UnusualOccurenceAction unusualOccurenceAction;

	public UnusualOccurenceListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public UnusualOccurence getUnusualOccurence() {
		return unusualOccurence;
	}

	@Override
	public UnusualOccurence getInstance() {
		return getUnusualOccurence();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('unusualOccurence', 'view')}")
	public List<UnusualOccurence> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<UnusualOccurence> getEntityClass() {
		return UnusualOccurence.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"unusualOccurence.id = #{unusualOccurenceList.unusualOccurence.id}",

			"unusualOccurence.archived = #{unusualOccurenceList.unusualOccurence.archived}",

			"unusualOccurence.occurenceType.id = #{unusualOccurenceList.unusualOccurence.occurenceType.id}",

			"unusualOccurence.category = #{unusualOccurenceList.unusualOccurence.category}",

			"unusualOccurence.severity = #{unusualOccurenceList.unusualOccurence.severity}",

			"lower(unusualOccurence.description) like concat(lower(#{unusualOccurenceList.unusualOccurence.description}),'%')",

			"unusualOccurence.patient.id = #{unusualOccurenceList.unusualOccurence.patient.id}",

			"unusualOccurence.dateCreated <= #{unusualOccurenceList.dateCreatedRange.end}",
			"unusualOccurence.dateCreated >= #{unusualOccurenceList.dateCreatedRange.begin}",};

	public List<UnusualOccurence> getUnusualOccurencesByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		unusualOccurence.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedUnusualOccurence")
	public void onArchive() {
		refresh();
	}

	public void setOccurenceTypeId(Long id) {
		if (unusualOccurence.getOccurenceType() == null) {
			unusualOccurence
					.setOccurenceType(new com.oreon.cerebrum.unusualoccurences.OccurenceType());
		}
		unusualOccurence.getOccurenceType().setId(id);
	}

	public Long getOccurenceTypeId() {
		return unusualOccurence.getOccurenceType() == null
				? null
				: unusualOccurence.getOccurenceType().getId();
	}

	public void setPatientId(Long id) {
		if (unusualOccurence.getPatient() == null) {
			unusualOccurence
					.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		unusualOccurence.getPatient().setId(id);
	}

	public Long getPatientId() {
		return unusualOccurence.getPatient() == null ? null : unusualOccurence
				.getPatient().getId();
	}

	//@Restrict("#{s:hasPermission('unusualOccurence', 'delete')}")
	public void archiveById(Long id) {
		unusualOccurenceAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, UnusualOccurence e) {

		builder.append("\""
				+ (e.getOccurenceType() != null ? e.getOccurenceType()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getCategory() != null ? e.getCategory() : "")
				+ "\",");

		builder.append("\"" + (e.getSeverity() != null ? e.getSeverity() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("OccurenceType" + ",");

		builder.append("Category" + ",");

		builder.append("Severity" + ",");

		builder.append("Description" + ",");

		builder.append("Patient" + ",");

		builder.append("\r\n");
	}
}
