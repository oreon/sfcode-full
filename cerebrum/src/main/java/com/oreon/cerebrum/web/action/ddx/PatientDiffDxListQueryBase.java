package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.PatientDiffDx;

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

import com.oreon.cerebrum.ddx.PatientDiffDx;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientDiffDxListQueryBase
		extends
			BaseQuery<PatientDiffDx, Long> {

	private static final String EJBQL = "select patientDiffDx from PatientDiffDx patientDiffDx";

	protected PatientDiffDx patientDiffDx = new PatientDiffDx();

	public PatientDiffDx getPatientDiffDx() {
		return patientDiffDx;
	}

	@Override
	public PatientDiffDx getInstance() {
		return getPatientDiffDx();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('patientDiffDx', 'view')}")
	public List<PatientDiffDx> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PatientDiffDx> getEntityClass() {
		return PatientDiffDx.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"patientDiffDx.id = #{patientDiffDxList.patientDiffDx.id}",

			"patientDiffDx.archived = #{patientDiffDxList.patientDiffDx.archived}",

			"patientDiffDx.patient.id = #{patientDiffDxList.patientDiffDx.patient.id}",

			"lower(patientDiffDx.notes) like concat(lower(#{patientDiffDxList.patientDiffDx.notes}),'%')",

			"patientDiffDx.dateCreated <= #{patientDiffDxList.dateCreatedRange.end}",
			"patientDiffDx.dateCreated >= #{patientDiffDxList.dateCreatedRange.begin}",};

	@Observer("archivedPatientDiffDx")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, PatientDiffDx e) {

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getNotes() != null ? e.getNotes().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Patient" + ",");

		builder.append("Notes" + ",");

		builder.append("\r\n");
	}
}
