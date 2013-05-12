package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.VitalValue;

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

public abstract class VitalValueActionBase extends BaseAction<VitalValue>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private VitalValue vitalValue;

	@In(create = true, value = "trackedVitalAction")
	com.oreon.cerebrum.web.action.patient.TrackedVitalAction trackedVitalAction;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setVitalValueId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		vitalValue = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setVitalValueIdForModalDlg(Long id) {
		setId(id);
		vitalValue = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setTrackedVitalId(Long id) {

		if (id != null && id > 0)
			getInstance().setTrackedVital(trackedVitalAction.loadFromId(id));

	}

	public Long getTrackedVitalId() {
		if (getInstance().getTrackedVital() != null)
			return getInstance().getTrackedVital().getId();
		return 0L;
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

	public Long getVitalValueId() {
		return (Long) getId();
	}

	public VitalValue getEntity() {
		return vitalValue;
	}

	//@Override
	public void setEntity(VitalValue t) {
		this.vitalValue = t;
		loadAssociations();
	}

	public VitalValue getVitalValue() {
		return (VitalValue) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('vitalValue', 'edit'}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('vitalValue', 'delete'}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected VitalValue createInstance() {
		VitalValue instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.patient.TrackedVital trackedVital = trackedVitalAction
				.getDefinedInstance();
		if (trackedVital != null && isNew()) {
			getInstance().setTrackedVital(trackedVital);
		}

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public boolean isWired() {
		return true;
	}

	public VitalValue getDefinedInstance() {
		return (VitalValue) (isIdDefined() ? getInstance() : null);
	}

	public void setVitalValue(VitalValue t) {
		this.vitalValue = t;
		if (vitalValue != null)
			setVitalValueId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<VitalValue> getEntityClass() {
		return VitalValue.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (vitalValue.getTrackedVital() != null) {
			criteria = criteria.add(Restrictions.eq("trackedVital.id",
					vitalValue.getTrackedVital().getId()));
		}

		if (vitalValue.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", vitalValue
					.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (vitalValue.getTrackedVital() != null) {
			trackedVitalAction.setInstance(getInstance().getTrackedVital());
			trackedVitalAction.loadAssociations();
		}

		if (vitalValue.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
			patientAction.loadAssociations();
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewVitalValue() {
		load(currentEntityId);
		return "viewVitalValue";
	}

}
