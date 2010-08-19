package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.ListingSummary;

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

public abstract class ListingSummaryActionBase
		extends
			BaseAction<ListingSummary> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ListingSummary listingSummary;

	@DataModel
	private List<ListingSummary> listingSummaryRecordList;

	public void setListingSummaryId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getListingSummaryId() {
		return (Long) getId();
	}

	//@Factory("listingSummaryRecordList")
	//@Observer("archivedListingSummary")
	public void findRecords() {
		//search();
	}

	public ListingSummary getEntity() {
		return listingSummary;
	}

	@Override
	public void setEntity(ListingSummary t) {
		this.listingSummary = t;
		loadAssociations();
	}

	public ListingSummary getListingSummary() {
		return getInstance();
	}

	@Override
	protected ListingSummary createInstance() {
		return new ListingSummary();
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

	public ListingSummary getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setListingSummary(ListingSummary t) {
		this.listingSummary = t;
		loadAssociations();
	}

	@Override
	public Class<ListingSummary> getEntityClass() {
		return ListingSummary.class;
	}

	@Override
	public void setEntityList(List<ListingSummary> list) {
		this.listingSummaryRecordList = list;
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

	public List<ListingSummary> getEntityList() {
		if (listingSummaryRecordList == null) {
			findRecords();
		}
		return listingSummaryRecordList;
	}

}
