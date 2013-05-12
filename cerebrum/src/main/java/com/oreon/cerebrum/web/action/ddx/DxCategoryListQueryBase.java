package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.DxCategory;

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

import com.oreon.cerebrum.ddx.DxCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DxCategoryListQueryBase
		extends
			BaseQuery<DxCategory, Long> {

	private static final String EJBQL = "select dxCategory from DxCategory dxCategory";

	protected DxCategory dxCategory = new DxCategory();

	public DxCategory getDxCategory() {
		return dxCategory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	@Restrict("#{s:hasPermission('dxCategory', 'view')}")
	public List<DxCategory> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<DxCategory> getEntityClass() {
		return DxCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dxCategory.id = #{dxCategoryList.dxCategory.id}",

			"lower(dxCategory.name) like concat(lower(#{dxCategoryList.dxCategory.name}),'%')",

			"dxCategory.dateCreated <= #{dxCategoryList.dateCreatedRange.end}",
			"dxCategory.dateCreated >= #{dxCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedDxCategory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DxCategory e) {

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
