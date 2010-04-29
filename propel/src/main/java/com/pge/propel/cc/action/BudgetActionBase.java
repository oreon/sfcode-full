package com.pge.propel.cc.action;

import com.pge.propel.cc.Budget;

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

import com.pge.propel.cc.LineItem;

public class BudgetActionBase extends BaseAction<Budget>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Budget budget;

	@DataModel
	private List<Budget> budgetList;

	@Factory("budgetList")
	@Observer("archivedBudget")
	public void findRecords() {
		search();
	}

	public Budget getEntity() {
		return budget;
	}

	@Override
	public void setEntity(Budget t) {
		this.budget = t;
	}

	@Override
	public void setEntityList(List<Budget> list) {
		this.budgetList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (budget.getProject() != null) {
			criteria = criteria.add(Restrictions.eq("project.id", budget
					.getProject().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<LineItem> listLineItem;

	void initListLineItem() {
		listLineItem = new ArrayList<LineItem>();
		if (budget.getLineItem().isEmpty()) {

			addLineItem();

		} else
			listLineItem.addAll(budget.getLineItem());
	}

	public List<LineItem> getListLineItem() {
		if (listLineItem == null) {
			initListLineItem();
		}
		return listLineItem;
	}

	public void setListLineItem(List<LineItem> listLineItem) {
		this.listLineItem = listLineItem;
	}

	public void deleteLineItem(LineItem lineItem) {
		listLineItem.remove(lineItem);
	}

	@Begin(join = true)
	public void addLineItem() {
		LineItem lineItem = new LineItem();

		lineItem.setBudget(budget);

		listLineItem.add(lineItem);
	}

	public void updateComposedAssociations() {

		budget.getLineItem().clear();
		budget.getLineItem().addAll(listLineItem);

	}

	public List<Budget> getEntityList() {
		if (budgetList == null) {
			findRecords();
		}
		return budgetList;
	}

}
