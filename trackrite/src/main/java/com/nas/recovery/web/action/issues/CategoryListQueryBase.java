package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Category;

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

import org.wc.trackrite.issues.Category;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CategoryListQueryBase extends BaseQuery<Category, Long> {

	private static final String EJBQL = "select category from Category category";

	protected Category category = new Category();

	public Category getCategory() {
		return category;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Category> getEntityClass() {
		return Category.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"category.id = #{categoryList.category.id}",

			"lower(category.name) like concat(lower(#{categoryList.category.name}),'%')",

			"category.dateCreated <= #{categoryList.dateCreatedRange.end}",
			"category.dateCreated >= #{categoryList.dateCreatedRange.begin}",};

	@Observer("archivedCategory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Category e) {

		if (e.getName() != null)

			builder.append(e.getName() + ",");

		builder.append(",");

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
