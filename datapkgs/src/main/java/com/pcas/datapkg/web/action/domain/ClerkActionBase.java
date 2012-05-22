package com.pcas.datapkg.web.action.domain;

import com.pcas.datapkg.domain.Clerk;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class ClerkActionBase extends BaseAction<Clerk>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Clerk clerk;

	@DataModel
	private List<Clerk> clerkRecordList;

	public void setClerkId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setClerkIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getClerkId() {
		return (Long) getId();
	}

	public Clerk getEntity() {
		return clerk;
	}

	//@Override
	public void setEntity(Clerk t) {
		this.clerk = t;
		loadAssociations();
	}

	public Clerk getClerk() {
		return (Clerk) getInstance();
	}

	@Override
	protected Clerk createInstance() {
		Clerk instance = super.createInstance();

		return instance;
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

	public Clerk getDefinedInstance() {
		return (Clerk) (isIdDefined() ? getInstance() : null);
	}

	public void setClerk(Clerk t) {
		this.clerk = t;
		if (clerk != null)
			setClerkId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Clerk> getEntityClass() {
		return Clerk.class;
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

	public void clearLists() {

	}

	/** 
	 * []
	 */
	public String register() {

		return null;

	}

	/** 
	 * []
	 */
	public String login() {

		return null;

	}

	public com.pcas.datapkg.domain.Employee findByPhone(String phone) {

		return executeSingleResultNamedQuery("findByPhone", phone);

	}

	/** 
	 * []
	 */
	public String retrieveCredentials() {

		return null;

	}

	public String viewClerk() {
		load(currentEntityId);
		return "viewClerk";
	}

}
