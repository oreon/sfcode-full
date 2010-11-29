package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.PrescriptionItem;

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

public abstract class PrescriptionItemActionBase
		extends
			BaseAction<PrescriptionItem> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private PrescriptionItem prescriptionItem;

	@In(create = true, value = "drugAction")
	com.nas.recovery.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "prescriptionAction")
	com.nas.recovery.web.action.patient.PrescriptionAction prescriptionAction;

	@DataModel
	private List<PrescriptionItem> prescriptionItemRecordList;

	public void setPrescriptionItemId(Long id) {
		if (id == 0) {
			clearInstance();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPrescriptionItemIdForModalDlg(Long id) {
		setId(id);
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

	public void setPrescriptionId(Long id) {

		if (id != null && id > 0)
			getInstance().setPrescription(prescriptionAction.loadFromId(id));

	}

	public Long getPrescriptionId() {
		if (getInstance().getPrescription() != null)
			return getInstance().getPrescription().getId();
		return 0L;
	}

	public Long getPrescriptionItemId() {
		return (Long) getId();
	}

	public PrescriptionItem getEntity() {
		return prescriptionItem;
	}

	//@Override
	public void setEntity(PrescriptionItem t) {
		this.prescriptionItem = t;
		loadAssociations();
	}

	public PrescriptionItem getPrescriptionItem() {
		return (PrescriptionItem) getInstance();
	}

	@Override
	protected PrescriptionItem createInstance() {
		return new PrescriptionItem();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.callosum.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null) {
			getInstance().setDrug(drug);
		}

		com.oreon.callosum.patient.Prescription prescription = prescriptionAction
				.getDefinedInstance();
		if (prescription != null) {
			getInstance().setPrescription(prescription);
		}

	}

	public boolean isWired() {
		return true;
	}

	public PrescriptionItem getDefinedInstance() {
		return (PrescriptionItem) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescriptionItem(PrescriptionItem t) {
		this.prescriptionItem = t;
		loadAssociations();
	}

	@Override
	public Class<PrescriptionItem> getEntityClass() {
		return PrescriptionItem.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (prescriptionItem.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", prescriptionItem
					.getDrug().getId()));
		}

		if (prescriptionItem.getPrescription() != null) {
			criteria = criteria.add(Restrictions.eq("prescription.id",
					prescriptionItem.getPrescription().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (prescriptionItem.getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
		}

		if (prescriptionItem.getPrescription() != null) {
			prescriptionAction.setInstance(getInstance().getPrescription());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

}
