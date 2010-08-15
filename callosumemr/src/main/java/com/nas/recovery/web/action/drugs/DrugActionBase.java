package com.nas.recovery.web.action.drugs;

import com.oreon.callosum.drugs.Drug;

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

public abstract class DrugActionBase extends BaseAction<Drug>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Drug drug;

	@DataModel
	private List<Drug> drugRecordList;

	public void setDrugId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getDrugId() {
		return (Long) getId();
	}

	//@Factory("drugRecordList")
	//@Observer("archivedDrug")
	public void findRecords() {
		//search();
	}

	public Drug getEntity() {
		return drug;
	}

	@Override
	public void setEntity(Drug t) {
		this.drug = t;
		loadAssociations();
	}

	public Drug getDrug() {
		return getInstance();
	}

	@Override
	protected Drug createInstance() {
		return new Drug();
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

	public Drug getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setDrug(Drug t) {
		this.drug = t;
		loadAssociations();
	}

	@Override
	public Class<Drug> getEntityClass() {
		return Drug.class;
	}

	@Override
	public void setEntityList(List<Drug> list) {
		this.drugRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Drug> getEntityList() {
		if (drugRecordList == null) {
			findRecords();
		}
		return drugRecordList;
	}

}
