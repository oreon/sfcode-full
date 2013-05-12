package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.PrescriptionTemplate;

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

import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;

public abstract class PrescriptionTemplateActionBase
		extends
			BaseAction<PrescriptionTemplate> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private PrescriptionTemplate prescriptionTemplate;

	public void setPrescriptionTemplateId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		prescriptionTemplate = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPrescriptionTemplateIdForModalDlg(Long id) {
		setId(id);
		prescriptionTemplate = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getPrescriptionTemplateId() {
		return (Long) getId();
	}

	public PrescriptionTemplate getEntity() {
		return prescriptionTemplate;
	}

	//@Override
	public void setEntity(PrescriptionTemplate t) {
		this.prescriptionTemplate = t;
		loadAssociations();
	}

	public PrescriptionTemplate getPrescriptionTemplate() {
		return (PrescriptionTemplate) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('prescriptionTemplate', 'edit'}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('prescriptionTemplate', 'delete'}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PrescriptionTemplate createInstance() {
		PrescriptionTemplate instance = super.createInstance();

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

	public PrescriptionTemplate getDefinedInstance() {
		return (PrescriptionTemplate) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescriptionTemplate(PrescriptionTemplate t) {
		this.prescriptionTemplate = t;
		if (prescriptionTemplate != null)
			setPrescriptionTemplateId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<PrescriptionTemplate> getEntityClass() {
		return PrescriptionTemplate.class;
	}

	public com.oreon.cerebrum.prescription.PrescriptionTemplate findByUnqName(
			String name) {
		return executeSingleResultNamedQuery(
				"prescriptionTemplate.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListPrescriptionItemTemplates();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.prescription.PrescriptionItemTemplate> listPrescriptionItemTemplates = new ArrayList<com.oreon.cerebrum.prescription.PrescriptionItemTemplate>();

	void initListPrescriptionItemTemplates() {

		if (listPrescriptionItemTemplates.isEmpty())
			listPrescriptionItemTemplates.addAll(getInstance()
					.getPrescriptionItemTemplates());

	}

	public List<com.oreon.cerebrum.prescription.PrescriptionItemTemplate> getListPrescriptionItemTemplates() {

		prePopulateListPrescriptionItemTemplates();
		return listPrescriptionItemTemplates;
	}

	public void prePopulateListPrescriptionItemTemplates() {
	}

	public void setListPrescriptionItemTemplates(
			List<com.oreon.cerebrum.prescription.PrescriptionItemTemplate> listPrescriptionItemTemplates) {
		this.listPrescriptionItemTemplates = listPrescriptionItemTemplates;
	}

	public void deletePrescriptionItemTemplates(int index) {
		listPrescriptionItemTemplates.remove(index);
	}

	@Begin(join = true)
	public void addPrescriptionItemTemplates() {
		initListPrescriptionItemTemplates();
		PrescriptionItemTemplate prescriptionItemTemplates = new PrescriptionItemTemplate();

		prescriptionItemTemplates.setPrescriptionTemplate(getInstance());

		getListPrescriptionItemTemplates().add(prescriptionItemTemplates);
	}

	public void updateComposedAssociations() {

		if (listPrescriptionItemTemplates != null) {
			getInstance().getPrescriptionItemTemplates().clear();
			getInstance().getPrescriptionItemTemplates().addAll(
					listPrescriptionItemTemplates);
		}
	}

	public void clearLists() {
		listPrescriptionItemTemplates.clear();

	}

	public String viewPrescriptionTemplate() {
		load(currentEntityId);
		return "viewPrescriptionTemplate";
	}

}
