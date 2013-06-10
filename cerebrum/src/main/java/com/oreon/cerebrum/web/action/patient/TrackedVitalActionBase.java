package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.TrackedVital;

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

public abstract class TrackedVitalActionBase extends BaseAction<TrackedVital>
		implements
			java.io.Serializable {

	public void setTrackedVitalId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		instance = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setTrackedVitalIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getTrackedVitalId() {
		return (Long) getId();
	}

	public TrackedVital getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(TrackedVital t) {
		this.instance = t;
		loadAssociations();
	}

	public TrackedVital getTrackedVital() {
		return (TrackedVital) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('trackedVital', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('trackedVital', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected TrackedVital createInstance() {
		TrackedVital instance = super.createInstance();

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

	public TrackedVital getDefinedInstance() {
		return (TrackedVital) (isIdDefined() ? getInstance() : null);
	}

	public void setTrackedVital(TrackedVital t) {
		this.instance = t;
		if (getInstance() != null)
			setTrackedVitalId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<TrackedVital> getEntityClass() {
		return TrackedVital.class;
	}

	public com.oreon.cerebrum.patient.TrackedVital findByUnqName(String name) {
		return executeSingleResultNamedQuery("trackedVital.findByUnqName", name);
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

	public String viewTrackedVital() {
		load(currentEntityId);
		return "viewTrackedVital";
	}

}
