package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Clerk;

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
import org.jboss.seam.annotations.security.Restrict;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class ClerkActionBase
		extends
			com.oreon.cerebrum.web.action.employee.AbstractEmployeeAction<Clerk>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	private Clerk clerk;

	public void setClerkId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		clerk = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setClerkIdForModalDlg(Long id) {
		setId(id);
		clerk = loadInstance();
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
	//@Restrict("#{s:hasPermission('clerk', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('clerk', 'delete')}")
	public void archiveById() {
		super.archiveById();
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

	public String viewClerk() {
		load(currentEntityId);
		return "viewClerk";
	}

}
