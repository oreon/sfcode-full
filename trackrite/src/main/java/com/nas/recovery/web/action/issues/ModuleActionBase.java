package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Module;

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

public abstract class ModuleActionBase extends BaseAction<Module>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Module module;

	@DataModel
	private List<Module> moduleRecordList;

	public void setModuleId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	public Long getModuleId() {
		return (Long) getId();
	}

	//@Factory("moduleRecordList")
	//@Observer("archivedModule")
	public void findRecords() {
		//search();
	}

	public Module getEntity() {
		return module;
	}

	@Override
	public void setEntity(Module t) {
		this.module = t;
		loadAssociations();
	}

	public Module getModule() {
		return getInstance();
	}

	@Override
	protected Module createInstance() {
		return new Module();
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

	public Module getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setModule(Module t) {
		this.module = t;
		loadAssociations();
	}

	@Override
	public Class<Module> getEntityClass() {
		return Module.class;
	}

	@Override
	public void setEntityList(List<Module> list) {
		this.moduleRecordList = list;
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

	public List<Module> getEntityList() {
		if (moduleRecordList == null) {
			findRecords();
		}
		return moduleRecordList;
	}

}
