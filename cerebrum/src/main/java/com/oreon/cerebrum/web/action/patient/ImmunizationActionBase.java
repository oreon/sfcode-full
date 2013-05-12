package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Immunization;

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

public abstract class ImmunizationActionBase extends BaseAction<Immunization>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Immunization immunization;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "vaccineAction")
	com.oreon.cerebrum.web.action.patient.VaccineAction vaccineAction;

	public void setImmunizationId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		immunization = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setImmunizationIdForModalDlg(Long id) {
		setId(id);
		immunization = loadInstance();
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

	public void setVaccineId(Long id) {

		if (id != null && id > 0)
			getInstance().setVaccine(vaccineAction.loadFromId(id));

	}

	public Long getVaccineId() {
		if (getInstance().getVaccine() != null)
			return getInstance().getVaccine().getId();
		return 0L;
	}

	public Long getImmunizationId() {
		return (Long) getId();
	}

	public Immunization getEntity() {
		return immunization;
	}

	//@Override
	public void setEntity(Immunization t) {
		this.immunization = t;
		loadAssociations();
	}

	public Immunization getImmunization() {
		return (Immunization) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('immunization', 'edit'}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('immunization', 'delete'}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Immunization createInstance() {
		Immunization instance = super.createInstance();

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

		com.oreon.cerebrum.patient.Vaccine vaccine = vaccineAction
				.getDefinedInstance();
		if (vaccine != null && isNew()) {
			getInstance().setVaccine(vaccine);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Immunization getDefinedInstance() {
		return (Immunization) (isIdDefined() ? getInstance() : null);
	}

	public void setImmunization(Immunization t) {
		this.immunization = t;
		if (immunization != null)
			setImmunizationId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Immunization> getEntityClass() {
		return Immunization.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (immunization.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", immunization
					.getPatient().getId()));
		}

		if (immunization.getVaccine() != null) {
			criteria = criteria.add(Restrictions.eq("vaccine.id", immunization
					.getVaccine().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (immunization.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
			patientAction.loadAssociations();
		}

		if (immunization.getVaccine() != null) {
			vaccineAction.setInstance(getInstance().getVaccine());
			vaccineAction.loadAssociations();
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewImmunization() {
		load(currentEntityId);
		return "viewImmunization";
	}

}
