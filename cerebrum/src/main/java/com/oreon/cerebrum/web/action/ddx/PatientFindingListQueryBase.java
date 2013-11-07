package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.PatientFinding;

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

import com.oreon.cerebrum.ddx.PatientFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientFindingListQueryBase
		extends
			BaseQuery<PatientFinding, Long> {

	private static final String EJBQL = "select patientFinding from PatientFinding patientFinding";

	protected PatientFinding patientFinding = new PatientFinding();

	@In(create = true)
	PatientFindingAction patientFindingAction;

	public PatientFindingListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PatientFinding getPatientFinding() {
		return patientFinding;
	}

	@Override
	public PatientFinding getInstance() {
		return getPatientFinding();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('patientFinding', 'view')}")
	public List<PatientFinding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PatientFinding> getEntityClass() {
		return PatientFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"patientFinding.id = #{patientFindingList.patientFinding.id}",

			"patientFinding.archived = #{patientFindingList.patientFinding.archived}",

			"patientFinding.finding.id = #{patientFindingList.patientFinding.finding.id}",

			"patientFinding.patientDiffDx.id = #{patientFindingList.patientFinding.patientDiffDx.id}",

			"patientFinding.dateCreated <= #{patientFindingList.dateCreatedRange.end}",
			"patientFinding.dateCreated >= #{patientFindingList.dateCreatedRange.begin}",};

	public List<PatientFinding> getPatientFindingsByPatientDiffDx(
			com.oreon.cerebrum.ddx.PatientDiffDx patientDiffDx) {
		patientFinding.setPatientDiffDx(patientDiffDx);
		return getResultList();
	}

	@Observer("archivedPatientFinding")
	public void onArchive() {
		refresh();
	}

	public void setFindingId(Long id) {
		if (patientFinding.getFinding() == null) {
			patientFinding.setFinding(new com.oreon.cerebrum.ddx.Finding());
		}
		patientFinding.getFinding().setId(id);
	}

	public Long getFindingId() {
		return patientFinding.getFinding() == null ? null : patientFinding
				.getFinding().getId();
	}

	public void setPatientDiffDxId(Long id) {
		if (patientFinding.getPatientDiffDx() == null) {
			patientFinding
					.setPatientDiffDx(new com.oreon.cerebrum.ddx.PatientDiffDx());
		}
		patientFinding.getPatientDiffDx().setId(id);
	}

	public Long getPatientDiffDxId() {
		return patientFinding.getPatientDiffDx() == null
				? null
				: patientFinding.getPatientDiffDx().getId();
	}

	//@Restrict("#{s:hasPermission('patientFinding', 'delete')}")
	public void archiveById(Long id) {
		patientFindingAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, PatientFinding e) {

		builder.append("\""
				+ (e.getFinding() != null ? e.getFinding().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPatientDiffDx() != null ? e.getPatientDiffDx()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Finding" + ",");

		builder.append("PatientDiffDx" + ",");

		builder.append("\r\n");
	}
}
