package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.PropertyManager;

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
import org.jboss.seam.Component;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class PropertyManagerActionBase
		extends
			com.nas.recovery.web.action.loan.PersonAction<PropertyManager>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private PropertyManager propertyManager;

	@DataModel
	private List<PropertyManager> propertyManagerRecordList;

	public void setPropertyManagerId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getPropertyManagerId() {
		return (Long) getId();
	}

	//@Factory("propertyManagerRecordList")
	//@Observer("archivedPropertyManager")
	public void findRecords() {
		//search();
	}

	public PropertyManager getEntity() {
		return propertyManager;
	}

	@Override
	public void setEntity(PropertyManager t) {
		this.propertyManager = t;
		loadAssociations();
	}

	public PropertyManager getPropertyManager() {
		return getInstance();
	}

	@Override
	protected PropertyManager createInstance() {
		return new PropertyManager();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public PropertyManager getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setPropertyManager(PropertyManager t) {
		this.propertyManager = t;
		loadAssociations();
	}

	@Override
	public Class<PropertyManager> getEntityClass() {
		return PropertyManager.class;
	}

	@Override
	public void setEntityList(List<PropertyManager> list) {
		this.propertyManagerRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<PropertyManager> getEntityList() {
		if (propertyManagerRecordList == null) {
			findRecords();
		}
		return propertyManagerRecordList;
	}

}
