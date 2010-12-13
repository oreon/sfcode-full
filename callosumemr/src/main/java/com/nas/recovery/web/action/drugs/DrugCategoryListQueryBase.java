package com.nas.recovery.web.action.drugs;

import com.oreon.callosum.drugs.DrugCategory;

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

import com.oreon.callosum.drugs.DrugCategory;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class DrugCategoryListQueryBase
		extends
			BaseQuery<DrugCategory, Long> {

	private static final String EJBQL = "select drugCategory from DrugCategory drugCategory";

	protected DrugCategory drugCategory = new DrugCategory();

	public DrugCategory getDrugCategory() {
		return drugCategory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DrugCategory> getEntityClass() {
		return DrugCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"drugCategory.id = #{drugCategoryList.drugCategory.id}",

			"lower(drugCategory.name) like concat(lower(#{drugCategoryList.drugCategory.name}),'%')",

			"drugCategory.dateCreated <= #{drugCategoryList.dateCreatedRange.end}",
			"drugCategory.dateCreated >= #{drugCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedDrugCategory")
	public void onArchive() {
		refresh();
	}

}
