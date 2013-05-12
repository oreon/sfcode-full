package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.PhysicalFinding;

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

public abstract class PhysicalFindingActionBase
		extends
			com.oreon.cerebrum.web.action.ddx.AbstractFindingAction<PhysicalFinding>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private PhysicalFinding physicalFinding;

	public void setPhysicalFindingId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		physicalFinding = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPhysicalFindingIdForModalDlg(Long id) {
		setId(id);
		physicalFinding = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getPhysicalFindingId() {
		return (Long) getId();
	}

	public PhysicalFinding getEntity() {
		return physicalFinding;
	}

	//@Override
	public void setEntity(PhysicalFinding t) {
		this.physicalFinding = t;
		loadAssociations();
	}

	public PhysicalFinding getPhysicalFinding() {
		return (PhysicalFinding) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('physicalFinding', 'edit'}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('physicalFinding', 'delete'}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PhysicalFinding createInstance() {
		PhysicalFinding instance = super.createInstance();

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

	public PhysicalFinding getDefinedInstance() {
		return (PhysicalFinding) (isIdDefined() ? getInstance() : null);
	}

	public void setPhysicalFinding(PhysicalFinding t) {
		this.physicalFinding = t;
		if (physicalFinding != null)
			setPhysicalFindingId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<PhysicalFinding> getEntityClass() {
		return PhysicalFinding.class;
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

	public String viewPhysicalFinding() {
		load(currentEntityId);
		return "viewPhysicalFinding";
	}

}
