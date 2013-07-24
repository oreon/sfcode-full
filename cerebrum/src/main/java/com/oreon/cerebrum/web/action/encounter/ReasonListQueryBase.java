package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Reason;

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

import com.oreon.cerebrum.encounter.Reason;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ReasonListQueryBase extends BaseQuery<Reason, Long> {

	private static final String EJBQL = "select reason from Reason reason";

	protected Reason reason = new Reason();

	public Reason getReason() {
		return reason;
	}

	@Override
	public Reason getInstance() {
		return getReason();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('reason', 'view')}")
	public List<Reason> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Reason> getEntityClass() {
		return Reason.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"reason.id = #{reasonList.reason.id}",

			"reason.archived = #{reasonList.reason.archived}",

			"reason.encounter.id = #{reasonList.reason.encounter.id}",

			"reason.code.id = #{reasonList.reason.code.id}",

			"lower(reason.remarks) like concat(lower(#{reasonList.reason.remarks}),'%')",

			"reason.dateCreated <= #{reasonList.dateCreatedRange.end}",
			"reason.dateCreated >= #{reasonList.dateCreatedRange.begin}",};

	public List<Reason> getReasonsByEncounter(
			com.oreon.cerebrum.encounter.Encounter encounter) {
		reason.setEncounter(encounter);
		return getResultList();
	}

	@Observer("archivedReason")
	public void onArchive() {
		refresh();
	}

	public void setEncounterId(Long id) {
		if (reason.getEncounter() == null) {
			reason.setEncounter(new com.oreon.cerebrum.encounter.Encounter());
		}
		reason.getEncounter().setId(id);
	}

	public Long getEncounterId() {
		return reason.getEncounter() == null ? null : reason.getEncounter()
				.getId();
	}

	public void setCodeId(Long id) {
		if (reason.getCode() == null) {
			reason.setCode(new com.oreon.cerebrum.codes.Code());
		}
		reason.getCode().setId(id);
	}

	public Long getCodeId() {
		return reason.getCode() == null ? null : reason.getCode().getId();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Reason e) {

		builder.append("\""
				+ (e.getEncounter() != null ? e.getEncounter().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getCode() != null ? e.getCode().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Encounter" + ",");

		builder.append("Code" + ",");

		builder.append("Remarks" + ",");

		builder.append("\r\n");
	}
}
