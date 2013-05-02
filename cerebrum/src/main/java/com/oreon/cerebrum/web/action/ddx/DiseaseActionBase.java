package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.Disease;

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

import com.oreon.cerebrum.ddx.Disease;

public abstract class DiseaseActionBase extends BaseAction<Disease>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Disease disease;

	@In(create = true, value = "diseaseAction")
	com.oreon.cerebrum.web.action.ddx.DiseaseAction relatedDiseaseAction;

	@In(create = true, value = "conditionCategoryAction")
	com.oreon.cerebrum.web.action.ddx.ConditionCategoryAction conditionCategoryAction;

	@In(create = true, value = "diseaseAction")
	com.oreon.cerebrum.web.action.ddx.DiseaseAction differentialDiagnosesAction;

	public void setDiseaseId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		disease = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDiseaseIdForModalDlg(Long id) {
		setId(id);
		disease = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setRelatedDiseaseId(Long id) {

		if (id != null && id > 0)
			getInstance()
					.setRelatedDisease(relatedDiseaseAction.loadFromId(id));

	}

	public Long getRelatedDiseaseId() {
		if (getInstance().getRelatedDisease() != null)
			return getInstance().getRelatedDisease().getId();
		return 0L;
	}

	public void setConditionCategoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setConditionCategory(
					conditionCategoryAction.loadFromId(id));

	}

	public Long getConditionCategoryId() {
		if (getInstance().getConditionCategory() != null)
			return getInstance().getConditionCategory().getId();
		return 0L;
	}

	public Long getDiseaseId() {
		return (Long) getId();
	}

	public Disease getEntity() {
		return disease;
	}

	//@Override
	public void setEntity(Disease t) {
		this.disease = t;
		loadAssociations();
	}

	public Disease getDisease() {
		return (Disease) getInstance();
	}

	@Override
	protected Disease createInstance() {
		Disease instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.ddx.Disease relatedDisease = relatedDiseaseAction
				.getDefinedInstance();
		if (relatedDisease != null && isNew()) {
			getInstance().setRelatedDisease(relatedDisease);
		}

		com.oreon.cerebrum.ddx.ConditionCategory conditionCategory = conditionCategoryAction
				.getDefinedInstance();
		if (conditionCategory != null && isNew()) {
			getInstance().setConditionCategory(conditionCategory);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Disease getDefinedInstance() {
		return (Disease) (isIdDefined() ? getInstance() : null);
	}

	public void setDisease(Disease t) {
		this.disease = t;
		if (disease != null)
			setDiseaseId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Disease> getEntityClass() {
		return Disease.class;
	}

	public com.oreon.cerebrum.ddx.Disease findByUnqName(String name) {
		return executeSingleResultNamedQuery("disease.findByUnqName", name);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (disease.getRelatedDisease() != null) {
			criteria = criteria.add(Restrictions.eq("relatedDisease.id",
					disease.getRelatedDisease().getId()));
		}

		if (disease.getConditionCategory() != null) {
			criteria = criteria.add(Restrictions.eq("conditionCategory.id",
					disease.getConditionCategory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (disease.getRelatedDisease() != null) {
			relatedDiseaseAction.setInstance(getInstance().getRelatedDisease());
			relatedDiseaseAction.loadAssociations();
		}

		if (disease.getConditionCategory() != null) {
			conditionCategoryAction.setInstance(getInstance()
					.getConditionCategory());
			conditionCategoryAction.loadAssociations();
		}

		initListDifferentialDiagnoses();

	}

	public void updateAssociations() {

		com.oreon.cerebrum.ddx.Disease differentialDiagnoses = (com.oreon.cerebrum.ddx.Disease) org.jboss.seam.Component
				.getInstance("disease");
		differentialDiagnoses.setRelatedDisease(disease);
		events.raiseTransactionSuccessEvent("archivedDisease");

	}

	protected List<com.oreon.cerebrum.ddx.Disease> listDifferentialDiagnoses = new ArrayList<com.oreon.cerebrum.ddx.Disease>();

	void initListDifferentialDiagnoses() {

		if (listDifferentialDiagnoses.isEmpty())
			listDifferentialDiagnoses.addAll(getInstance()
					.getDifferentialDiagnoses());

	}

	public List<com.oreon.cerebrum.ddx.Disease> getListDifferentialDiagnoses() {

		prePopulateListDifferentialDiagnoses();
		return listDifferentialDiagnoses;
	}

	public void prePopulateListDifferentialDiagnoses() {
	}

	public void setListDifferentialDiagnoses(
			List<com.oreon.cerebrum.ddx.Disease> listDifferentialDiagnoses) {
		this.listDifferentialDiagnoses = listDifferentialDiagnoses;
	}

	public void deleteDifferentialDiagnoses(int index) {
		listDifferentialDiagnoses.remove(index);
	}

	@Begin(join = true)
	public void addDifferentialDiagnoses() {
		initListDifferentialDiagnoses();
		Disease differentialDiagnoses = new Disease();

		differentialDiagnoses.setRelatedDisease(getInstance());

		getListDifferentialDiagnoses().add(differentialDiagnoses);
	}

	public void updateComposedAssociations() {

		if (listDifferentialDiagnoses != null) {
			getInstance().getDifferentialDiagnoses().clear();
			getInstance().getDifferentialDiagnoses().addAll(
					listDifferentialDiagnoses);
		}
	}

	public void clearLists() {
		listDifferentialDiagnoses.clear();

	}

	public String viewDisease() {
		load(currentEntityId);
		return "viewDisease";
	}

}
