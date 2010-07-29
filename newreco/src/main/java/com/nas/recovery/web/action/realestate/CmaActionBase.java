package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Cma;

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

public abstract class CmaActionBase extends BaseAction<Cma>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Cma cma;

	@DataModel
	private List<Cma> cmaRecordList;

	public void setCmaId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getCmaId() {
		return (Long) getId();
	}

	//@Factory("cmaRecordList")
	//@Observer("archivedCma")
	public void findRecords() {
		//search();
	}

	public Cma getEntity() {
		return cma;
	}

	@Override
	public void setEntity(Cma t) {
		this.cma = t;
		loadAssociations();
	}

	public Cma getCma() {
		return getInstance();
	}

	@Override
	protected Cma createInstance() {
		return new Cma();
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

	public Cma getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setCma(Cma t) {
		this.cma = t;
		loadAssociations();
	}

	@Override
	public Class<Cma> getEntityClass() {
		return Cma.class;
	}

	@Override
	public void setEntityList(List<Cma> list) {
		this.cmaRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Cma> getEntityList() {
		if (cmaRecordList == null) {
			findRecords();
		}
		return cmaRecordList;
	}

}