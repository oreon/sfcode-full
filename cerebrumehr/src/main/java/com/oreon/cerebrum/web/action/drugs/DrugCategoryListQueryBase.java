package com.oreon.cerebrum.web.action.drugs;

import com.oreon.cerebrum.drugs.DrugCategory;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.drugs.DrugCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugCategoryListQueryBase
		extends
			BaseQuery<DrugCategory, Long> {

	private static final String EJBQL = "select drugCategory from DrugCategory drugCategory";

	protected DrugCategory drugCategory = new DrugCategory();

	public DrugCategoryListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public DrugCategory getDrugCategory() {
		return drugCategory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.drugs.Drug drugsToSearch;

	public void setDrugsToSearch(com.oreon.cerebrum.drugs.Drug drugToSearch) {
		this.drugsToSearch = drugToSearch;
	}

	public com.oreon.cerebrum.drugs.Drug getDrugsToSearch() {
		return drugsToSearch;
	}

	@Override
	public DrugCategory getInstance() {
		return getDrugCategory();
	}

	@Override
	//@Restrict("#{s:hasPermission('drugCategory', 'view')}")
	public List<DrugCategory> getResultList() {
		return super.getResultList();
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

			"drugCategory.archived = #{drugCategoryList.drugCategory.archived}",

			"lower(drugCategory.name) like concat(lower(#{drugCategoryList.drugCategory.name}),'%')",

			"#{drugCategoryList.drugsToSearch} in elements(drugCategory.drugs)",

			"drugCategory.dateCreated <= #{drugCategoryList.dateCreatedRange.end}",
			"drugCategory.dateCreated >= #{drugCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedDrugCategory")
	public void onArchive() {
		refresh();
	}

}
