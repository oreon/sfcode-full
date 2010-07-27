package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Legal;

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

public class LegalActionBase extends BaseAction<Legal>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Legal legal;

	@DataModel
	private List<Legal> legalRecordList;

	public void setLegalId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getLegalId() {
		return (Long) getId();
	}

	//@Factory("legalRecordList")
	//@Observer("archivedLegal")
	public void findRecords() {
		//search();
	}

	public Legal getEntity() {
		return legal;
	}

	@Override
	public void setEntity(Legal t) {
		this.legal = t;
		loadAssociations();
	}

	public Legal getLegal() {
		return getInstance();
	}

	@Override
	protected Legal createInstance() {
		return new Legal();
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

	public Legal getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setLegal(Legal t) {
		this.legal = t;
		loadAssociations();
	}

	@Override
	public Class<Legal> getEntityClass() {
		return Legal.class;
	}

	@Override
	public void setEntityList(List<Legal> list) {
		this.legalRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Legal> getEntityList() {
		if (legalRecordList == null) {
			findRecords();
		}
		return legalRecordList;
	}

}
