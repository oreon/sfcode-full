package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Differential;

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
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class DifferentialActionBase extends BaseAction<Differential>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long differentialId;

	@In(create = true, value = "encounterAction")
	com.oreon.cerebrum.web.action.encounter.EncounterAction encounterAction;

	public void setDifferentialId(Long id) {
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
	public void setDifferentialIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setEncounterId(Long id) {

		if (id != null && id > 0)
			getInstance().setEncounter(encounterAction.loadFromId(id));

	}

	public Long getEncounterId() {
		if (getInstance().getEncounter() != null)
			return getInstance().getEncounter().getId();
		return 0L;
	}

	public Long getDifferentialId() {
		return (Long) getId();
	}

	public Differential getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Differential t) {
		this.instance = t;
		loadAssociations();
	}

	public Differential getDifferential() {
		return (Differential) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('differential', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('differential', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Differential createInstance() {
		Differential instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}

	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.encounter.Encounter encounter = encounterAction
				.getDefinedInstance();
		if (encounter != null && isNew()) {
			getInstance().setEncounter(encounter);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Differential getDefinedInstance() {
		return (Differential) (isIdDefined() ? getInstance() : null);
	}

	public void setDifferential(Differential t) {
		this.instance = t;
		if (getInstance() != null)
			setDifferentialId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Differential> getEntityClass() {
		return Differential.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getEncounter() != null) {
			criteria = criteria.add(Restrictions.eq("encounter.id", instance
					.getEncounter().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getEncounter() != null) {
			encounterAction.setInstance(getInstance().getEncounter());
			encounterAction.loadAssociations();
		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewDifferential() {
		load(currentEntityId);
		return "viewDifferential";
	}

}
