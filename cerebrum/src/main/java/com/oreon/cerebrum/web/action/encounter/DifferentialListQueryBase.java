package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Differential;

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

import com.oreon.cerebrum.encounter.Differential;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DifferentialListQueryBase
		extends
			BaseQuery<Differential, Long> {

	private static final String EJBQL = "select differential from Differential differential";

	protected Differential differential = new Differential();

	public Differential getDifferential() {
		return differential;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	@Restrict("#{s:hasPermission('differential', 'view')}")
	public List<Differential> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Differential> getEntityClass() {
		return Differential.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"differential.id = #{differentialList.differential.id}",

			"differential.encounter.id = #{differentialList.differential.encounter.id}",

			"lower(differential.remarks) like concat(lower(#{differentialList.differential.remarks}),'%')",

			"differential.dateCreated <= #{differentialList.dateCreatedRange.end}",
			"differential.dateCreated >= #{differentialList.dateCreatedRange.begin}",};

	public List<Differential> getDifferentialsByEncounter(
			com.oreon.cerebrum.encounter.Encounter encounter) {
		//setMaxResults(10000);
		differential.setEncounter(encounter);
		return getResultList();
	}

	@Observer("archivedDifferential")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Differential e) {

		builder.append("\""
				+ (e.getEncounter() != null ? e.getEncounter().getDisplayName()
						.replace(",", "") : "") + "\",");

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

		builder.append("Remarks" + ",");

		builder.append("\r\n");
	}
}
