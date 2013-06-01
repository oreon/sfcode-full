package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.ConditionFinding;

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

public abstract class ConditionFindingActionBase
		extends
			BaseAction<ConditionFinding> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	private ConditionFinding conditionFinding;

	@In(create = true, value = "diseaseAction")
	com.oreon.cerebrum.web.action.ddx.DiseaseAction diseaseAction;

	public void setConditionFindingId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		conditionFinding = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setConditionFindingIdForModalDlg(Long id) {
		setId(id);
		conditionFinding = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setDiseaseId(Long id) {

		if (id != null && id > 0)
			getInstance().setDisease(diseaseAction.loadFromId(id));

	}

	public Long getDiseaseId() {
		if (getInstance().getDisease() != null)
			return getInstance().getDisease().getId();
		return 0L;
	}

	public Long getConditionFindingId() {
		return (Long) getId();
	}

	public ConditionFinding getEntity() {
		return conditionFinding;
	}

	//@Override
	public void setEntity(ConditionFinding t) {
		this.conditionFinding = t;
		loadAssociations();
	}

	public ConditionFinding getConditionFinding() {
		return (ConditionFinding) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('conditionFinding', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('conditionFinding', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected ConditionFinding createInstance() {
		ConditionFinding instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.ddx.Disease disease = diseaseAction
				.getDefinedInstance();
		if (disease != null && isNew()) {
			getInstance().setDisease(disease);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ConditionFinding getDefinedInstance() {
		return (ConditionFinding) (isIdDefined() ? getInstance() : null);
	}

	public void setConditionFinding(ConditionFinding t) {
		this.conditionFinding = t;
		if (conditionFinding != null)
			setConditionFindingId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<ConditionFinding> getEntityClass() {
		return ConditionFinding.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (conditionFinding.getDisease() != null) {
			criteria = criteria.add(Restrictions.eq("disease.id",
					conditionFinding.getDisease().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (conditionFinding.getDisease() != null) {
			diseaseAction.setInstance(getInstance().getDisease());
			diseaseAction.loadAssociations();
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewConditionFinding() {
		load(currentEntityId);
		return "viewConditionFinding";
	}

}
