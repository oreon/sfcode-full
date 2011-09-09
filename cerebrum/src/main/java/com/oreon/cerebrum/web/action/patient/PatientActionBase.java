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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.oreon.cerebrum.patient.Admission;
import com.oreon.cerebrum.patient.Prescription;
import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;
import com.oreon.cerebrum.patient.Document;
import com.oreon.cerebrum.patient.Allergy;
import com.oreon.cerebrum.patient.Immunization;

public abstract class PatientActionBase
		extends
			com.oreon.cerebrum.web.action.patient.PersonAction<Patient>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Patient patient;

	@In(create = true, value = "admissionAction")
	com.oreon.cerebrum.web.action.patient.AdmissionAction admissionsAction;

	@In(create = true, value = "prescriptionAction")
	com.oreon.cerebrum.web.action.patient.PrescriptionAction prescriptionsAction;

	@In(create = true, value = "unusualOccurenceAction")
	com.oreon.cerebrum.web.action.unusualoccurences.UnusualOccurenceAction unusualOccurencesAction;

	@In(create = true, value = "documentAction")
	com.oreon.cerebrum.web.action.patient.DocumentAction documentsAction;

	@In(create = true, value = "allergyAction")
	com.oreon.cerebrum.web.action.patient.AllergyAction allergysAction;

	@In(create = true, value = "immunizationAction")
	com.oreon.cerebrum.web.action.patient.ImmunizationAction immunizationsAction;

	@DataModel
	private List<Patient> patientRecordList;

	public void setPatientId(Long id) {
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
	public void setPatientIdForModalDlg(Long id) {
		setId(id);
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

		initListDocuments();

		initListAllergys();

		initListImmunizations();

	}

	public void updateAssociations() {

		com.oreon.cerebrum.patient.Admission admissions = (com.oreon.cerebrum.patient.Admission) org.jboss.seam.Component
				.getInstance("admission");
		admissions.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedAdmission");

		com.oreon.cerebrum.patient.Prescription prescriptions = (com.oreon.cerebrum.patient.Prescription) org.jboss.seam.Component
				.getInstance("prescription");
		prescriptions.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedPrescription");

		com.oreon.cerebrum.unusualoccurences.UnusualOccurence unusualOccurences = (com.oreon.cerebrum.unusualoccurences.UnusualOccurence) org.jboss.seam.Component
				.getInstance("unusualOccurence");
		unusualOccurences.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedUnusualOccurence");

		com.oreon.cerebrum.patient.Document documents = (com.oreon.cerebrum.patient.Document) org.jboss.seam.Component
				.getInstance("document");
		documents.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedDocument");

		com.oreon.cerebrum.patient.Allergy allergys = (com.oreon.cerebrum.patient.Allergy) org.jboss.seam.Component
				.getInstance("allergy");
		allergys.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedAllergy");

		com.oreon.cerebrum.patient.Immunization immunizations = (com.oreon.cerebrum.patient.Immunization) org.jboss.seam.Component
				.getInstance("immunization");
		immunizations.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedImmunization");

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

	protected List<com.oreon.cerebrum.patient.Prescription> listPrescriptions = new ArrayList<com.oreon.cerebrum.patient.Prescription>();

	void initListPrescriptions() {

		if (listPrescriptions.isEmpty())
			listPrescriptions.addAll(getInstance().getPrescriptions());

	}

	public List<com.oreon.cerebrum.patient.Prescription> getListPrescriptions() {

		prePopulateListPrescriptions();
		return listPrescriptions;
	}

	public void prePopulateListPrescriptions() {
	}

	public void setListPrescriptions(
			List<com.oreon.cerebrum.patient.Prescription> listPrescriptions) {
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

	protected List<com.oreon.cerebrum.patient.Document> listDocuments = new ArrayList<com.oreon.cerebrum.patient.Document>();

	void initListDocuments() {

		if (listDocuments.isEmpty())
			listDocuments.addAll(getInstance().getDocuments());

	}

	public List<com.oreon.cerebrum.patient.Document> getListDocuments() {

		prePopulateListDocuments();
		return listDocuments;
	}

	public void prePopulateListDocuments() {
	}

	public void setListDocuments(
			List<com.oreon.cerebrum.patient.Document> listDocuments) {
		this.listDocuments = listDocuments;
	}

	public void deleteDocuments(int index) {
		listDocuments.remove(index);
	}

	@Begin(join = true)
	public void addDocuments() {
		initListDocuments();
		Document documents = new Document();

		documents.setPatient(getInstance());

		getListDocuments().add(documents);
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

		if (listDocuments != null) {
			getInstance().getDocuments().clear();
			getInstance().getDocuments().addAll(listDocuments);
		}

		if (listAllergys != null) {
			getInstance().getAllergys().clear();
			getInstance().getAllergys().addAll(listAllergys);
		}

		if (listImmunizations != null) {
			getInstance().getImmunizations().clear();
			getInstance().getImmunizations().addAll(listImmunizations);
		}
	}

	public void clearLists() {
		listAdmissions.clear();
		listPrescriptions.clear();
		listUnusualOccurences.clear();
		listDocuments.clear();
		listAllergys.clear();
		listImmunizations.clear();

	}

	public String viewPatient() {
		load(currentEntityId);
		return "viewPatient";
	}

}
