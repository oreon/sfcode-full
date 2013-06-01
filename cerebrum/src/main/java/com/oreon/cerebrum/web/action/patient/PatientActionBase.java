package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Patient;

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

import com.oreon.cerebrum.patient.Admission;
import com.oreon.cerebrum.prescription.Prescription;
import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;
import com.oreon.cerebrum.patient.PatientDocument;
import com.oreon.cerebrum.patient.Allergy;
import com.oreon.cerebrum.patient.Immunization;
import com.oreon.cerebrum.patient.VitalValue;
import com.oreon.cerebrum.encounter.Encounter;

public abstract class PatientActionBase
		extends
			com.oreon.cerebrum.web.action.patient.PersonAction<Patient>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	private Patient patient;

	@In(create = true, value = "admissionAction")
	com.oreon.cerebrum.web.action.patient.AdmissionAction admissionsAction;

	@In(create = true, value = "prescriptionAction")
	com.oreon.cerebrum.web.action.prescription.PrescriptionAction prescriptionsAction;

	@In(create = true, value = "unusualOccurenceAction")
	com.oreon.cerebrum.web.action.unusualoccurences.UnusualOccurenceAction unusualOccurencesAction;

	@In(create = true, value = "patientDocumentAction")
	com.oreon.cerebrum.web.action.patient.PatientDocumentAction patientDocumentsAction;

	@In(create = true, value = "allergyAction")
	com.oreon.cerebrum.web.action.patient.AllergyAction allergysAction;

	@In(create = true, value = "immunizationAction")
	com.oreon.cerebrum.web.action.patient.ImmunizationAction immunizationsAction;

	@In(create = true, value = "vitalValueAction")
	com.oreon.cerebrum.web.action.patient.VitalValueAction vitalValuesAction;

	@In(create = true, value = "encounterAction")
	com.oreon.cerebrum.web.action.encounter.EncounterAction encountersAction;

	public void setPatientId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		patient = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPatientIdForModalDlg(Long id) {
		setId(id);
		patient = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getPatientId() {
		return (Long) getId();
	}

	public Patient getEntity() {
		return patient;
	}

	//@Override
	public void setEntity(Patient t) {
		this.patient = t;
		loadAssociations();
	}

	public Patient getPatient() {
		return (Patient) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('patient', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('patient', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Patient createInstance() {
		Patient instance = super.createInstance();

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

	public Patient getDefinedInstance() {
		return (Patient) (isIdDefined() ? getInstance() : null);
	}

	public void setPatient(Patient t) {
		this.patient = t;
		if (patient != null)
			setPatientId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Patient> getEntityClass() {
		return Patient.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListAdmissions();

		initListPrescriptions();

		initListUnusualOccurences();

		initListPatientDocuments();

		initListAllergys();

		initListImmunizations();

		initListVitalValues();

		initListEncounters();

	}

	public void updateAssociations() {

		com.oreon.cerebrum.patient.Admission admissions = (com.oreon.cerebrum.patient.Admission) org.jboss.seam.Component
				.getInstance("admission");
		admissions.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedAdmission");

		com.oreon.cerebrum.prescription.Prescription prescriptions = (com.oreon.cerebrum.prescription.Prescription) org.jboss.seam.Component
				.getInstance("prescription");
		prescriptions.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedPrescription");

		com.oreon.cerebrum.unusualoccurences.UnusualOccurence unusualOccurences = (com.oreon.cerebrum.unusualoccurences.UnusualOccurence) org.jboss.seam.Component
				.getInstance("unusualOccurence");
		unusualOccurences.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedUnusualOccurence");

		com.oreon.cerebrum.patient.PatientDocument patientDocuments = (com.oreon.cerebrum.patient.PatientDocument) org.jboss.seam.Component
				.getInstance("patientDocument");
		patientDocuments.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedPatientDocument");

		com.oreon.cerebrum.patient.Allergy allergys = (com.oreon.cerebrum.patient.Allergy) org.jboss.seam.Component
				.getInstance("allergy");
		allergys.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedAllergy");

		com.oreon.cerebrum.patient.Immunization immunizations = (com.oreon.cerebrum.patient.Immunization) org.jboss.seam.Component
				.getInstance("immunization");
		immunizations.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedImmunization");

		com.oreon.cerebrum.patient.VitalValue vitalValues = (com.oreon.cerebrum.patient.VitalValue) org.jboss.seam.Component
				.getInstance("vitalValue");
		vitalValues.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedVitalValue");

		com.oreon.cerebrum.encounter.Encounter encounters = (com.oreon.cerebrum.encounter.Encounter) org.jboss.seam.Component
				.getInstance("encounter");
		encounters.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedEncounter");

	}

	protected List<com.oreon.cerebrum.patient.Admission> listAdmissions = new ArrayList<com.oreon.cerebrum.patient.Admission>();

	void initListAdmissions() {

		if (listAdmissions.isEmpty())
			listAdmissions.addAll(getInstance().getAdmissions());

	}

	public List<com.oreon.cerebrum.patient.Admission> getListAdmissions() {

		prePopulateListAdmissions();
		return listAdmissions;
	}

	public void prePopulateListAdmissions() {
	}

	public void setListAdmissions(
			List<com.oreon.cerebrum.patient.Admission> listAdmissions) {
		this.listAdmissions = listAdmissions;
	}

	public void deleteAdmissions(int index) {
		listAdmissions.remove(index);
	}

	@Begin(join = true)
	public void addAdmissions() {

		initListAdmissions();
		Admission admissions = new Admission();

		admissions.setPatient(getInstance());

		getListAdmissions().add(admissions);

	}

	protected List<com.oreon.cerebrum.prescription.Prescription> listPrescriptions = new ArrayList<com.oreon.cerebrum.prescription.Prescription>();

	void initListPrescriptions() {

		if (listPrescriptions.isEmpty())
			listPrescriptions.addAll(getInstance().getPrescriptions());

	}

	public List<com.oreon.cerebrum.prescription.Prescription> getListPrescriptions() {

		prePopulateListPrescriptions();
		return listPrescriptions;
	}

	public void prePopulateListPrescriptions() {
	}

	public void setListPrescriptions(
			List<com.oreon.cerebrum.prescription.Prescription> listPrescriptions) {
		this.listPrescriptions = listPrescriptions;
	}

	public void deletePrescriptions(int index) {
		listPrescriptions.remove(index);
	}

	@Begin(join = true)
	public void addPrescriptions() {

		initListPrescriptions();
		Prescription prescriptions = new Prescription();

		prescriptions.setPatient(getInstance());

		getListPrescriptions().add(prescriptions);

	}

	protected List<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> listUnusualOccurences = new ArrayList<com.oreon.cerebrum.unusualoccurences.UnusualOccurence>();

	void initListUnusualOccurences() {

		if (listUnusualOccurences.isEmpty())
			listUnusualOccurences.addAll(getInstance().getUnusualOccurences());

	}

	public List<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> getListUnusualOccurences() {

		prePopulateListUnusualOccurences();
		return listUnusualOccurences;
	}

	public void prePopulateListUnusualOccurences() {
	}

	public void setListUnusualOccurences(
			List<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> listUnusualOccurences) {
		this.listUnusualOccurences = listUnusualOccurences;
	}

	public void deleteUnusualOccurences(int index) {
		listUnusualOccurences.remove(index);
	}

	@Begin(join = true)
	public void addUnusualOccurences() {

		initListUnusualOccurences();
		UnusualOccurence unusualOccurences = new UnusualOccurence();

		unusualOccurences.setPatient(getInstance());

		getListUnusualOccurences().add(unusualOccurences);

	}

	protected List<com.oreon.cerebrum.patient.PatientDocument> listPatientDocuments = new ArrayList<com.oreon.cerebrum.patient.PatientDocument>();

	void initListPatientDocuments() {

		if (listPatientDocuments.isEmpty())
			listPatientDocuments.addAll(getInstance().getPatientDocuments());

	}

	public List<com.oreon.cerebrum.patient.PatientDocument> getListPatientDocuments() {

		prePopulateListPatientDocuments();
		return listPatientDocuments;
	}

	public void prePopulateListPatientDocuments() {
	}

	public void setListPatientDocuments(
			List<com.oreon.cerebrum.patient.PatientDocument> listPatientDocuments) {
		this.listPatientDocuments = listPatientDocuments;
	}

	public void deletePatientDocuments(int index) {
		listPatientDocuments.remove(index);
	}

	@Begin(join = true)
	public void addPatientDocuments() {

		initListPatientDocuments();
		PatientDocument patientDocuments = new PatientDocument();

		patientDocuments.setPatient(getInstance());

		getListPatientDocuments().add(patientDocuments);

	}

	protected List<com.oreon.cerebrum.patient.Allergy> listAllergys = new ArrayList<com.oreon.cerebrum.patient.Allergy>();

	void initListAllergys() {

		if (listAllergys.isEmpty())
			listAllergys.addAll(getInstance().getAllergys());

	}

	public List<com.oreon.cerebrum.patient.Allergy> getListAllergys() {

		prePopulateListAllergys();
		return listAllergys;
	}

	public void prePopulateListAllergys() {
	}

	public void setListAllergys(
			List<com.oreon.cerebrum.patient.Allergy> listAllergys) {
		this.listAllergys = listAllergys;
	}

	public void deleteAllergys(int index) {
		listAllergys.remove(index);
	}

	@Begin(join = true)
	public void addAllergys() {

		initListAllergys();
		Allergy allergys = new Allergy();

		allergys.setPatient(getInstance());

		getListAllergys().add(allergys);

	}

	protected List<com.oreon.cerebrum.patient.Immunization> listImmunizations = new ArrayList<com.oreon.cerebrum.patient.Immunization>();

	void initListImmunizations() {

		if (listImmunizations.isEmpty())
			listImmunizations.addAll(getInstance().getImmunizations());

	}

	public List<com.oreon.cerebrum.patient.Immunization> getListImmunizations() {

		prePopulateListImmunizations();
		return listImmunizations;
	}

	public void prePopulateListImmunizations() {
	}

	public void setListImmunizations(
			List<com.oreon.cerebrum.patient.Immunization> listImmunizations) {
		this.listImmunizations = listImmunizations;
	}

	public void deleteImmunizations(int index) {
		listImmunizations.remove(index);
	}

	@Begin(join = true)
	public void addImmunizations() {

		initListImmunizations();
		Immunization immunizations = new Immunization();

		immunizations.setPatient(getInstance());

		getListImmunizations().add(immunizations);

	}

	protected List<com.oreon.cerebrum.patient.VitalValue> listVitalValues = new ArrayList<com.oreon.cerebrum.patient.VitalValue>();

	void initListVitalValues() {

		if (listVitalValues.isEmpty())
			listVitalValues.addAll(getInstance().getVitalValues());

	}

	public List<com.oreon.cerebrum.patient.VitalValue> getListVitalValues() {

		prePopulateListVitalValues();
		return listVitalValues;
	}

	public void prePopulateListVitalValues() {
	}

	public void setListVitalValues(
			List<com.oreon.cerebrum.patient.VitalValue> listVitalValues) {
		this.listVitalValues = listVitalValues;
	}

	public void deleteVitalValues(int index) {
		listVitalValues.remove(index);
	}

	@Begin(join = true)
	public void addVitalValues() {

		initListVitalValues();
		VitalValue vitalValues = new VitalValue();

		vitalValues.setPatient(getInstance());

		getListVitalValues().add(vitalValues);

	}

	protected List<com.oreon.cerebrum.encounter.Encounter> listEncounters = new ArrayList<com.oreon.cerebrum.encounter.Encounter>();

	void initListEncounters() {

		if (listEncounters.isEmpty())
			listEncounters.addAll(getInstance().getEncounters());

	}

	public List<com.oreon.cerebrum.encounter.Encounter> getListEncounters() {

		prePopulateListEncounters();
		return listEncounters;
	}

	public void prePopulateListEncounters() {
	}

	public void setListEncounters(
			List<com.oreon.cerebrum.encounter.Encounter> listEncounters) {
		this.listEncounters = listEncounters;
	}

	public void deleteEncounters(int index) {
		listEncounters.remove(index);
	}

	@Begin(join = true)
	public void addEncounters() {

		initListEncounters();
		Encounter encounters = new Encounter();

		encounters.setPatient(getInstance());

		getListEncounters().add(encounters);

	}

	public void updateComposedAssociations() {

		if (listAdmissions != null) {
			getInstance().getAdmissions().clear();
			getInstance().getAdmissions().addAll(listAdmissions);
		}

		if (listPrescriptions != null) {
			getInstance().getPrescriptions().clear();
			getInstance().getPrescriptions().addAll(listPrescriptions);
		}

		if (listUnusualOccurences != null) {
			getInstance().getUnusualOccurences().clear();
			getInstance().getUnusualOccurences().addAll(listUnusualOccurences);
		}

		if (listPatientDocuments != null) {
			getInstance().getPatientDocuments().clear();
			getInstance().getPatientDocuments().addAll(listPatientDocuments);
		}

		if (listAllergys != null) {
			getInstance().getAllergys().clear();
			getInstance().getAllergys().addAll(listAllergys);
		}

		if (listImmunizations != null) {
			getInstance().getImmunizations().clear();
			getInstance().getImmunizations().addAll(listImmunizations);
		}

		if (listVitalValues != null) {
			getInstance().getVitalValues().clear();
			getInstance().getVitalValues().addAll(listVitalValues);
		}

		if (listEncounters != null) {
			getInstance().getEncounters().clear();
			getInstance().getEncounters().addAll(listEncounters);
		}
	}

	public void clearLists() {
		listAdmissions.clear();
		listPrescriptions.clear();
		listUnusualOccurences.clear();
		listPatientDocuments.clear();
		listAllergys.clear();
		listImmunizations.clear();
		listVitalValues.clear();
		listEncounters.clear();

	}

	public String viewPatient() {
		load(currentEntityId);
		return "viewPatient";
	}

}
