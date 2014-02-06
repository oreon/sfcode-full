package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Reason;

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

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class ReasonActionBase extends BaseAction<Reason>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long reasonId;

	@In(create = true, value = "encounterAction")
	com.oreon.cerebrum.web.action.encounter.EncounterAction encounterAction;

	@In(create = true, value = "codeAction")
	com.oreon.cerebrum.web.action.codes.CodeAction codeAction;

	public void setReasonId(Long id) {
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
	public void setReasonIdForModalDlg(Long id) {
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

	public void setCodeId(Long id) {

		if (id != null && id > 0)
			getInstance().setCode(codeAction.loadFromId(id));

	}

	public Long getCodeId() {
		if (getInstance().getCode() != null)
			return getInstance().getCode().getId();
		return 0L;
	}

	public Long getReasonId() {
		return (Long) getId();
	}

	public Reason getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Reason t) {
		this.instance = t;
		loadAssociations();
	}

	public Reason getReason() {
		return (Reason) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('reason', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('reason', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Reason createInstance() {
		Reason instance = super.createInstance();

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

		com.oreon.cerebrum.codes.Code code = codeAction.getDefinedInstance();
		if (code != null && isNew()) {
			getInstance().setCode(code);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Reason getDefinedInstance() {
		return (Reason) (isIdDefined() ? getInstance() : null);
	}

	public void setReason(Reason t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setReasonId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Reason> getEntityClass() {
		return Reason.class;
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

		if (instance.getCode() != null) {
			criteria = criteria.add(Restrictions.eq("code.id", instance
					.getCode().getId()));
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

		if (getInstance().getCode() != null) {
			codeAction.setInstance(getInstance().getCode());
			codeAction.loadAssociations();
		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewReason() {
		load(currentEntityId);
		return "viewReason";
	}

}