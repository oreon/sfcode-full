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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class PrescriptionItemTemplateActionBase
		extends
			BaseAction<PrescriptionItemTemplate>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private PrescriptionItemTemplate prescriptionItemTemplate;

	@In(create = true, value = "drugAction")
	com.oreon.cerebrum.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "frequecyAction")
	com.oreon.cerebrum.web.action.prescription.FrequecyAction frequecyAction;

	@In(create = true, value = "prescriptionTemplateAction")
	com.oreon.cerebrum.web.action.prescription.PrescriptionTemplateAction prescriptionTemplateAction;

	//@DataModel
	//private List<PrescriptionItemTemplate> prescriptionItemTemplateRecordList;	

	public void setPrescriptionItemTemplateId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		prescriptionItemTemplate = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPrescriptionItemTemplateIdForModalDlg(Long id) {
		setId(id);
		prescriptionItemTemplate = loadInstance();
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

	public void setFrequecyId(Long id) {

		if (id != null && id > 0)
			getInstance().setFrequecy(frequecyAction.loadFromId(id));

	}

	public Long getFrequecyId() {
		if (getInstance().getFrequecy() != null)
			return getInstance().getFrequecy().getId();
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
		return prescriptionItemTemplate;
	}

	//@Override
	public void setEntity(PrescriptionItemTemplate t) {
		this.prescriptionItemTemplate = t;
		loadAssociations();
	}

	public PrescriptionItemTemplate getPrescriptionItemTemplate() {
		return (PrescriptionItemTemplate) getInstance();
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

	public void wire() {
		getInstance();

		com.oreon.cerebrum.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null && isNew()) {
			getInstance().setDrug(drug);
		}

		com.oreon.cerebrum.prescription.Frequecy frequecy = frequecyAction
				.getDefinedInstance();
		if (frequecy != null && isNew()) {
			getInstance().setFrequecy(frequecy);
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
		this.prescriptionItemTemplate = t;
		if (prescriptionItemTemplate != null)
			setPrescriptionItemTemplateId(t.getId());
		loadAssociations();
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

		if (prescriptionItemTemplate.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id",
					prescriptionItemTemplate.getDrug().getId()));
		}

		if (prescriptionItemTemplate.getFrequecy() != null) {
			criteria = criteria.add(Restrictions.eq("frequecy.id",
					prescriptionItemTemplate.getFrequecy().getId()));
		}

		if (prescriptionItemTemplate.getPrescriptionTemplate() != null) {
			criteria = criteria
					.add(Restrictions.eq("prescriptionTemplate.id",
							prescriptionItemTemplate.getPrescriptionTemplate()
									.getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (prescriptionItemTemplate.getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
			drugAction.loadAssociations();
		}

		if (prescriptionItemTemplate.getFrequecy() != null) {
			frequecyAction.setInstance(getInstance().getFrequecy());
			frequecyAction.loadAssociations();
		}

		if (prescriptionItemTemplate.getPrescriptionTemplate() != null) {
			prescriptionTemplateAction.setInstance(getInstance()
					.getPrescriptionTemplate());
			prescriptionTemplateAction.loadAssociations();
		}

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