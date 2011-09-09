package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Admission;

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

public abstract class AdmissionActionBase extends BaseAction<Admission>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Admission admission;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "bedAction")
	com.oreon.cerebrum.web.action.facility.BedAction bedAction;

	@DataModel
	private List<Admission> admissionRecordList;

	public void setAdmissionId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
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
	public void setAdmissionIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public void setBedId(Long id) {

		if (id != null && id > 0)
			getInstance().setBed(bedAction.loadFromId(id));

	}

	public Long getBedId() {
		if (getInstance().getBed() != null)
			return getInstance().getBed().getId();
		return 0L;
	}

	public Long getAdmissionId() {
		return (Long) getId();
	}

	public Admission getEntity() {
		return admission;
	}

	//@Override
	public void setEntity(Admission t) {
		this.admission = t;
		loadAssociations();
	}

	public Admission getAdmission() {
		return (Admission) getInstance();
	}

	@Override
	protected Admission createInstance() {
		Admission instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

		com.oreon.cerebrum.facility.Bed bed = bedAction.getDefinedInstance();
		if (bed != null && isNew()) {
			getInstance().setBed(bed);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Admission getDefinedInstance() {
		return (Admission) (isIdDefined() ? getInstance() : null);
	}

	public void setAdmission(Admission t) {
		this.admission = t;
		if (admission != null)
			setAdmissionId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Admission> getEntityClass() {
		return Admission.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (admission.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", admission
					.getPatient().getId()));
		}

		if (admission.getBed() != null) {
			criteria = criteria.add(Restrictions.eq("bed.id", admission
					.getBed().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (admission.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

		if (admission.getBed() != null) {
			bedAction.setInstance(getInstance().getBed());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewAdmission() {
		load(currentEntityId);
		return "viewAdmission";
	}

}
