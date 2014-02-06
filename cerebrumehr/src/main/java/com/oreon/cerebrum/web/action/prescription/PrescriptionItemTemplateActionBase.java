package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;

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

public abstract class PrescriptionItemTemplateActionBase
		extends
			BaseAction<PrescriptionItemTemplate>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long prescriptionItemTemplateId;

	@In(create = true, value = "drugAction")
	com.oreon.cerebrum.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "frequencyAction")
	com.oreon.cerebrum.web.action.prescription.FrequencyAction frequencyAction;

	@In(create = true, value = "prescriptionTemplateAction")
	com.oreon.cerebrum.web.action.prescription.PrescriptionTemplateAction prescriptionTemplateAction;

	public void setPrescriptionItemTemplateId(Long id) {
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
	public void setPrescriptionItemTemplateIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setDrugId(Long id) {

		if (id != null && id > 0)
			getInstance().setDrug(drugAction.loadFromId(id));

	}

	public Long getDrugId() {
		if (getInstance().getDrug() != null)
			return getInstance().getDrug().getId();
		return 0L;
	}

	public void setFrequencyId(Long id) {

		if (id != null && id > 0)
			getInstance().setFrequency(frequencyAction.loadFromId(id));

	}

	public Long getFrequencyId() {
		if (getInstance().getFrequency() != null)
			return getInstance().getFrequency().getId();
		return 0L;
	}

	public void setPrescriptionTemplateId(Long id) {

		if (id != null && id > 0)
			getInstance().setPrescriptionTemplate(
					prescriptionTemplateAction.loadFromId(id));

	}

	public Long getPrescriptionTemplateId() {
		if (getInstance().getPrescriptionTemplate() != null)
			return getInstance().getPrescriptionTemplate().getId();
		return 0L;
	}

	public Long getPrescriptionItemTemplateId() {
		return (Long) getId();
	}

	public PrescriptionItemTemplate getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(PrescriptionItemTemplate t) {
		this.instance = t;
		loadAssociations();
	}

	public PrescriptionItemTemplate getPrescriptionItemTemplate() {
		return (PrescriptionItemTemplate) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('prescriptionItemTemplate', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('prescriptionItemTemplate', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PrescriptionItemTemplate createInstance() {
		PrescriptionItemTemplate instance = super.createInstance();

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

		com.oreon.cerebrum.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null && isNew()) {
			getInstance().setDrug(drug);
		}

		com.oreon.cerebrum.prescription.Frequency frequency = frequencyAction
				.getDefinedInstance();
		if (frequency != null && isNew()) {
			getInstance().setFrequency(frequency);
		}

		com.oreon.cerebrum.prescription.PrescriptionTemplate prescriptionTemplate = prescriptionTemplateAction
				.getDefinedInstance();
		if (prescriptionTemplate != null && isNew()) {
			getInstance().setPrescriptionTemplate(prescriptionTemplate);
		}

	}

	public boolean isWired() {
		return true;
	}

	public PrescriptionItemTemplate getDefinedInstance() {
		return (PrescriptionItemTemplate) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescriptionItemTemplate(PrescriptionItemTemplate t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPrescriptionItemTemplateId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<PrescriptionItemTemplate> getEntityClass() {
		return PrescriptionItemTemplate.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", instance
					.getDrug().getId()));
		}

		if (instance.getFrequency() != null) {
			criteria = criteria.add(Restrictions.eq("frequency.id", instance
					.getFrequency().getId()));
		}

		if (instance.getPrescriptionTemplate() != null) {
			criteria = criteria.add(Restrictions.eq("prescriptionTemplate.id",
					instance.getPrescriptionTemplate().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
			drugAction.loadAssociations();
		}

		if (getInstance().getFrequency() != null) {
			frequencyAction.setInstance(getInstance().getFrequency());
			frequencyAction.loadAssociations();
		}

		if (getInstance().getPrescriptionTemplate() != null) {
			prescriptionTemplateAction.setInstance(getInstance()
					.getPrescriptionTemplate());
			prescriptionTemplateAction.loadAssociations();
		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewPrescriptionItemTemplate() {
		load(currentEntityId);
		return "viewPrescriptionItemTemplate";
	}

}