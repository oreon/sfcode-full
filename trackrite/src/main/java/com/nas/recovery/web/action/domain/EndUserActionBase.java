package com.nas.recovery.web.action.domain;

import org.wc.trackrite.domain.EndUser;

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
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class EndUserActionBase
		extends
			com.nas.recovery.web.action.domain.PersonAction<EndUser>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private EndUser endUser;

	@DataModel
	private List<EndUser> endUserRecordList;

	public void setEndUserId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setEndUserIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getEndUserId() {
		return (Long) getId();
	}

	//@Factory("endUserRecordList")
	//@Observer("archivedEndUser")
	public void findRecords() {
		//search();
	}

	public EndUser getEntity() {
		return endUser;
	}

	@Override
	public void setEntity(EndUser t) {
		this.endUser = t;
		loadAssociations();
	}

	public EndUser getEndUser() {
		return getInstance();
	}

	@Override
	protected EndUser createInstance() {
		return new EndUser();
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

	public EndUser getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setEndUser(EndUser t) {
		this.endUser = t;
		loadAssociations();
	}

	@Override
	public Class<EndUser> getEntityClass() {
		return EndUser.class;
	}

	@Override
	public void setEntityList(List<EndUser> list) {
		this.endUserRecordList = list;
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

	public List<EndUser> getEntityList() {
		if (endUserRecordList == null) {
			findRecords();
		}
		return endUserRecordList;
	}

}
