package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Encounter;

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

import com.oreon.cerebrum.encounter.PrescribedTest;
import com.oreon.cerebrum.encounter.Differential;

public abstract class EncounterActionBase extends BaseAction<Encounter>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Encounter encounter;

	@In(create = true, value = "physicianAction")
	com.oreon.cerebrum.web.action.employee.PhysicianAction physicianAction;

	@In(create = true, value = "prescriptionAction")
	com.oreon.cerebrum.web.action.patient.PrescriptionAction prescriptionAction;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@DataModel
	private List<Encounter> encounterRecordList;

	public void setEncounterId(Long id) {
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
	public void setEncounterIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setPhysicianId(Long id) {

		if (id != null && id > 0)
			getInstance().setPhysician(physicianAction.loadFromId(id));

	}

	public Long getPhysicianId() {
		if (getInstance().getPhysician() != null)
			return getInstance().getPhysician().getId();
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

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public Long getEncounterId() {
		return (Long) getId();
	}

	public Encounter getEntity() {
		return encounter;
	}

	//@Override
	public void setEntity(Encounter t) {
		this.encounter = t;
		loadAssociations();
	}

	public Encounter getEncounter() {
		return (Encounter) getInstance();
	}

	@Override
	protected Encounter createInstance() {
		Encounter instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.employee.Physician physician = physicianAction
				.getDefinedInstance();
		if (physician != null && isNew()) {
			getInstance().setPhysician(physician);
		}

		com.oreon.cerebrum.patient.Prescription prescription = prescriptionAction
				.getDefinedInstance();
		if (prescription != null && isNew()) {
			getInstance().setPrescription(prescription);
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

	public Encounter getDefinedInstance() {
		return (Encounter) (isIdDefined() ? getInstance() : null);
	}

	public void setEncounter(Encounter t) {
		this.encounter = t;
		if (encounter != null)
			setEncounterId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Encounter> getEntityClass() {
		return Encounter.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (encounter.getPhysician() != null) {
			criteria = criteria.add(Restrictions.eq("physician.id", encounter
					.getPhysician().getId()));
		}

		if (encounter.getPrescription() != null) {
			criteria = criteria.add(Restrictions.eq("prescription.id",
					encounter.getPrescription().getId()));
		}

		if (encounter.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", encounter
					.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (encounter.getPhysician() != null) {
			physicianAction.setInstance(getInstance().getPhysician());
		}

		if (encounter.getPrescription() != null) {
			prescriptionAction.setInstance(getInstance().getPrescription());
		}

		if (encounter.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

		initListPrescribedTests();

		initListDifferentials();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.encounter.PrescribedTest> listPrescribedTests = new ArrayList<com.oreon.cerebrum.encounter.PrescribedTest>();

	void initListPrescribedTests() {

		if (listPrescribedTests.isEmpty())
			listPrescribedTests.addAll(getInstance().getPrescribedTests());

	}

	public List<com.oreon.cerebrum.encounter.PrescribedTest> getListPrescribedTests() {

		prePopulateListPrescribedTests();
		return listPrescribedTests;
	}

	public void prePopulateListPrescribedTests() {
	}

	public void setListPrescribedTests(
			List<com.oreon.cerebrum.encounter.PrescribedTest> listPrescribedTests) {
		this.listPrescribedTests = listPrescribedTests;
	}

	public void deletePrescribedTests(int index) {
		listPrescribedTests.remove(index);
	}

	@Begin(join = true)
	public void addPrescribedTests() {
		initListPrescribedTests();
		PrescribedTest prescribedTests = new PrescribedTest();

		prescribedTests.setEncounter(getInstance());

		getListPrescribedTests().add(prescribedTests);
	}

	protected List<com.oreon.cerebrum.encounter.Differential> listDifferentials = new ArrayList<com.oreon.cerebrum.encounter.Differential>();

	void initListDifferentials() {

		if (listDifferentials.isEmpty())
			listDifferentials.addAll(getInstance().getDifferentials());

	}

	public List<com.oreon.cerebrum.encounter.Differential> getListDifferentials() {

		prePopulateListDifferentials();
		return listDifferentials;
	}

	public void prePopulateListDifferentials() {
	}

	public void setListDifferentials(
			List<com.oreon.cerebrum.encounter.Differential> listDifferentials) {
		this.listDifferentials = listDifferentials;
	}

	public void deleteDifferentials(int index) {
		listDifferentials.remove(index);
	}

	@Begin(join = true)
	public void addDifferentials() {
		initListDifferentials();
		Differential differentials = new Differential();

		differentials.setEncounter(getInstance());

		getListDifferentials().add(differentials);
	}

	public void updateComposedAssociations() {

		if (listPrescribedTests != null) {
			getInstance().getPrescribedTests().clear();
			getInstance().getPrescribedTests().addAll(listPrescribedTests);
		}

		if (listDifferentials != null) {
			getInstance().getDifferentials().clear();
			getInstance().getDifferentials().addAll(listDifferentials);
		}
	}

	public void clearLists() {
		listPrescribedTests.clear();
		listDifferentials.clear();

	}

	public String viewEncounter() {
		load(currentEntityId);
		return "viewEncounter";
	}

}
