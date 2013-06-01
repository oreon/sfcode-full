package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.ConditionFinding;

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

import com.oreon.cerebrum.ddx.ConditionFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ConditionFindingListQueryBase
		extends
			BaseQuery<ConditionFinding, Long> {

	private static final String EJBQL = "select conditionFinding from ConditionFinding conditionFinding";

	protected ConditionFinding conditionFinding = new ConditionFinding();

	public ConditionFinding getConditionFinding() {
		return conditionFinding;
	}

	@Override
	public ConditionFinding getInstance() {
		return getConditionFinding();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('conditionFinding', 'view')}")
	public List<ConditionFinding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ConditionFinding> getEntityClass() {
		return ConditionFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"conditionFinding.id = #{conditionFindingList.conditionFinding.id}",

			"conditionFinding.archived = #{conditionFindingList.conditionFinding.archived}",

			"conditionFinding.disease.id = #{conditionFindingList.conditionFinding.disease.id}",

			"conditionFinding.falsePositive = #{conditionFindingList.conditionFinding.falsePositive}",

			"conditionFinding.dateCreated <= #{conditionFindingList.dateCreatedRange.end}",
			"conditionFinding.dateCreated >= #{conditionFindingList.dateCreatedRange.begin}",};

	@Observer("archivedConditionFinding")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ConditionFinding e) {

		builder.append("\""
				+ (e.getDisease() != null ? e.getDisease().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getFalsePositive() != null ? e.getFalsePositive() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Disease" + ",");

		builder.append("FalsePositive" + ",");

		builder.append("\r\n");
	}
}
