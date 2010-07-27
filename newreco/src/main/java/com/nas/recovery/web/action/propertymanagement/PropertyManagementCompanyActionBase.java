package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.PropertyManagementCompany;

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

public class PropertyManagementCompanyActionBase
		extends
			BaseAction<PropertyManagementCompany>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private PropertyManagementCompany propertyManagementCompany;

	@DataModel
	private List<PropertyManagementCompany> propertyManagementCompanyRecordList;

	public void setPropertyManagementCompanyId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getPropertyManagementCompanyId() {
		return (Long) getId();
	}

	//@Factory("propertyManagementCompanyRecordList")
	//@Observer("archivedPropertyManagementCompany")
	public void findRecords() {
		//search();
	}

	public PropertyManagementCompany getEntity() {
		return propertyManagementCompany;
	}

	@Override
	public void setEntity(PropertyManagementCompany t) {
		this.propertyManagementCompany = t;
		loadAssociations();
	}

	public PropertyManagementCompany getPropertyManagementCompany() {
		return getInstance();
	}

	@Override
	protected PropertyManagementCompany createInstance() {
		return new PropertyManagementCompany();
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

	public PropertyManagementCompany getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setPropertyManagementCompany(PropertyManagementCompany t) {
		this.propertyManagementCompany = t;
		loadAssociations();
	}

	@Override
	public Class<PropertyManagementCompany> getEntityClass() {
		return PropertyManagementCompany.class;
	}

	@Override
	public void setEntityList(List<PropertyManagementCompany> list) {
		this.propertyManagementCompanyRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<PropertyManagementCompany> getEntityList() {
		if (propertyManagementCompanyRecordList == null) {
			findRecords();
		}
		return propertyManagementCompanyRecordList;
	}

}
