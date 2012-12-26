package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.Frequecy;

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

public abstract class FrequecyActionBase extends BaseAction<Frequecy>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Frequecy frequecy;

	//@DataModel
	//private List<Frequecy> frequecyRecordList;	

	public void setFrequecyId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		frequecy = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setFrequecyIdForModalDlg(Long id) {
		setId(id);
		frequecy = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getFrequecyId() {
		return (Long) getId();
	}

	public Frequecy getEntity() {
		return frequecy;
	}

	//@Override
	public void setEntity(Frequecy t) {
		this.frequecy = t;
		loadAssociations();
	}

	public Frequecy getFrequecy() {
		return (Frequecy) getInstance();
	}

	@Override
	protected Frequecy createInstance() {
		Frequecy instance = super.createInstance();

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

	public Frequecy getDefinedInstance() {
		return (Frequecy) (isIdDefined() ? getInstance() : null);
	}

	public void setFrequecy(Frequecy t) {
		this.frequecy = t;
		if (frequecy != null)
			setFrequecyId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Frequecy> getEntityClass() {
		return Frequecy.class;
	}

	public com.oreon.cerebrum.prescription.Frequecy findByUnqName(String name) {
		return executeSingleResultNamedQuery("frequecy.findByUnqName", name);
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

	public String viewFrequecy() {
		load(currentEntityId);
		return "viewFrequecy";
	}

}
