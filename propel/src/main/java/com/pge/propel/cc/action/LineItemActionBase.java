package com.pge.propel.cc.action;

import com.pge.propel.cc.LineItem;

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

public class LineItemActionBase extends BaseAction<LineItem>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private LineItem lineItem;

	@DataModel
	private List<LineItem> lineItemList;

	@Factory("lineItemList")
	@Observer("archivedLineItem")
	public void findRecords() {
		search();
	}

	public LineItem getEntity() {
		return lineItem;
	}

	@Override
	public void setEntity(LineItem t) {
		this.lineItem = t;
	}

	@Override
	public void setEntityList(List<LineItem> list) {
		this.lineItemList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (lineItem.getProductionService() != null) {
			criteria = criteria.add(Restrictions.eq("productionService.id",
					lineItem.getProductionService().getId()));
		}

		if (lineItem.getBudget() != null) {
			criteria = criteria.add(Restrictions.eq("budget.id", lineItem
					.getBudget().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<LineItem> getEntityList() {
		if (lineItemList == null) {
			findRecords();
		}
		return lineItemList;
	}

}
