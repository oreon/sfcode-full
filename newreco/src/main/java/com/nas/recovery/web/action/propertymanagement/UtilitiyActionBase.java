package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.Utilitiy;

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

public abstract class UtilitiyActionBase extends BaseAction<Utilitiy>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Utilitiy utilitiy;

	@DataModel
	private List<Utilitiy> utilitiyRecordList;

	public void setUtilitiyId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getUtilitiyId() {
		return (Long) getId();
	}

	//@Factory("utilitiyRecordList")
	//@Observer("archivedUtilitiy")
	public void findRecords() {
		//search();
	}

	public Utilitiy getEntity() {
		return utilitiy;
	}

	@Override
	public void setEntity(Utilitiy t) {
		this.utilitiy = t;
		loadAssociations();
	}

	public Utilitiy getUtilitiy() {
		return getInstance();
	}

	@Override
	protected Utilitiy createInstance() {
		return new Utilitiy();
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

	public Utilitiy getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setUtilitiy(Utilitiy t) {
		this.utilitiy = t;
		loadAssociations();
	}

	@Override
	public Class<Utilitiy> getEntityClass() {
		return Utilitiy.class;
	}

	@Override
	public void setEntityList(List<Utilitiy> list) {
		this.utilitiyRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Utilitiy> getEntityList() {
		if (utilitiyRecordList == null) {
			findRecords();
		}
		return utilitiyRecordList;
	}

}
