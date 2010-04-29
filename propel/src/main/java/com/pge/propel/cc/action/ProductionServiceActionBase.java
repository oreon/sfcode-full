package com.pge.propel.cc.action;

import com.pge.propel.cc.ProductionService;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public class ProductionServiceActionBase extends BaseAction<ProductionService>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ProductionService productionService;

	@DataModel
	private List<ProductionService> productionServiceList;

	@Factory("productionServiceList")
	@Observer("archivedProductionService")
	public void findRecords() {
		search();
	}

	public ProductionService getEntity() {
		return productionService;
	}

	@Override
	public void setEntity(ProductionService t) {
		this.productionService = t;
	}

	@Override
	public void setEntityList(List<ProductionService> list) {
		this.productionServiceList = list;
	}

	public void updateAssociations() {

	}

	public List<ProductionService> getEntityList() {
		if (productionServiceList == null) {
			findRecords();
		}
		return productionServiceList;
	}

}
