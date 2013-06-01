package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.ConditionCategory;

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

import com.oreon.cerebrum.ddx.ConditionCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ConditionCategoryListQueryBase
		extends
			BaseQuery<ConditionCategory, Long> {

	private static final String EJBQL = "select conditionCategory from ConditionCategory conditionCategory";

	protected ConditionCategory conditionCategory = new ConditionCategory();

	public ConditionCategory getConditionCategory() {
		return conditionCategory;
	}

	@Override
	public ConditionCategory getInstance() {
		return getConditionCategory();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('conditionCategory', 'view')}")
	public List<ConditionCategory> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ConditionCategory> getEntityClass() {
		return ConditionCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"conditionCategory.id = #{conditionCategoryList.conditionCategory.id}",

			"conditionCategory.archived = #{conditionCategoryList.conditionCategory.archived}",

			"lower(conditionCategory.name) like concat(lower(#{conditionCategoryList.conditionCategory.name}),'%')",

			"conditionCategory.dateCreated <= #{conditionCategoryList.dateCreatedRange.end}",
			"conditionCategory.dateCreated >= #{conditionCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedConditionCategory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ConditionCategory e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
