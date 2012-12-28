package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.Frequency;

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

public abstract class FrequencyActionBase extends BaseAction<Frequency>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Frequency frequency;

	//@DataModel
	//private List<Frequency> frequencyRecordList;	

	public void setFrequencyId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		frequency = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setFrequencyIdForModalDlg(Long id) {
		setId(id);
		frequency = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getFrequencyId() {
		return (Long) getId();
	}

	public Frequency getEntity() {
		return frequency;
	}

	//@Override
	public void setEntity(Frequency t) {
		this.frequency = t;
		loadAssociations();
	}

	public Frequency getFrequency() {
		return (Frequency) getInstance();
	}

	@Override
	protected Frequency createInstance() {
		Frequency instance = super.createInstance();

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

	public Frequency getDefinedInstance() {
		return (Frequency) (isIdDefined() ? getInstance() : null);
	}

	public void setFrequency(Frequency t) {
		this.frequency = t;
		if (frequency != null)
			setFrequencyId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Frequency> getEntityClass() {
		return Frequency.class;
	}

	public com.oreon.cerebrum.prescription.Frequency findByUnqName(String name) {
		return executeSingleResultNamedQuery("frequency.findByUnqName", name);
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

	public String viewFrequency() {
		load(currentEntityId);
		return "viewFrequency";
	}

}
