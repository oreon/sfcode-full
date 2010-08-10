package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.Category;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.wc.jshopper.domain.Category;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class CategoryListQueryBase extends BaseQuery<Category, Long> {

	//private static final String EJBQL = "select category from Category category";

	private Category category = new Category();

	private static final String[] RESTRICTIONS = {
			"category.id = #{categoryList.category.id}",

			"lower(category.name) like concat(lower(#{categoryList.category.name}),'%')",

			"category.parent = #{categoryList.category.parent}",

			"category.dateCreated <= #{categoryList.dateCreatedRange.end}",
			"category.dateCreated >= #{categoryList.dateCreatedRange.begin}",};

	public Category getCategory() {
		return category;
	}

	@Override
	public Class<Category> getEntityClass() {
		return Category.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
