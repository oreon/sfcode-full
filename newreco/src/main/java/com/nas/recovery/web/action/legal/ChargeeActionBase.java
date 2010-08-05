package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Chargee;

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

public abstract class ChargeeActionBase extends BaseAction<Chargee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Chargee chargee;

	@DataModel
	private List<Chargee> chargeeRecordList;

	public void setChargeeId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getChargeeId() {
		return (Long) getId();
	}

	//@Factory("chargeeRecordList")
	//@Observer("archivedChargee")
	public void findRecords() {
		//search();
	}

	public Chargee getEntity() {
		return chargee;
	}

	@Override
	public void setEntity(Chargee t) {
		this.chargee = t;
		loadAssociations();
	}

	public Chargee getChargee() {
		return getInstance();
	}

	@Override
	protected Chargee createInstance() {
		return new Chargee();
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

	public Chargee getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setChargee(Chargee t) {
		this.chargee = t;
		loadAssociations();
	}

	@Override
	public Class<Chargee> getEntityClass() {
		return Chargee.class;
	}

	@Override
	public void setEntityList(List<Chargee> list) {
		this.chargeeRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Chargee> getEntityList() {
		if (chargeeRecordList == null) {
			findRecords();
		}
		return chargeeRecordList;
	}

}
