package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.RequestForApproval;

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

public abstract class RequestForApprovalActionBase
		extends
			BaseAction<RequestForApproval> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RequestForApproval requestForApproval;

	@DataModel
	private List<RequestForApproval> requestForApprovalRecordList;

	public void setRequestForApprovalId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getRequestForApprovalId() {
		return (Long) getId();
	}

	//@Factory("requestForApprovalRecordList")
	//@Observer("archivedRequestForApproval")
	public void findRecords() {
		//search();
	}

	public RequestForApproval getEntity() {
		return requestForApproval;
	}

	@Override
	public void setEntity(RequestForApproval t) {
		this.requestForApproval = t;
		loadAssociations();
	}

	public RequestForApproval getRequestForApproval() {
		return getInstance();
	}

	@Override
	protected RequestForApproval createInstance() {
		return new RequestForApproval();
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

	public RequestForApproval getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setRequestForApproval(RequestForApproval t) {
		this.requestForApproval = t;
		loadAssociations();
	}

	@Override
	public Class<RequestForApproval> getEntityClass() {
		return RequestForApproval.class;
	}

	@Override
	public void setEntityList(List<RequestForApproval> list) {
		this.requestForApprovalRecordList = list;
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

	public List<RequestForApproval> getEntityList() {
		if (requestForApprovalRecordList == null) {
			findRecords();
		}
		return requestForApprovalRecordList;
	}

}
