package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Patient;

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

import com.oreon.callosum.patient.Admission;
import com.oreon.callosum.patient.Prescription;
import com.oreon.callosum.unusualoccurences.UnusualOccurence;
import com.oreon.callosum.patient.Document;
import com.oreon.callosum.patient.Allergy;
import com.oreon.callosum.patient.Immunization;

public abstract class PatientActionBase
		extends
			com.nas.recovery.web.action.patient.PersonAction<Patient>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Patient patient;

	@In(create = true, value = "admissionAction")
	com.nas.recovery.web.action.patient.AdmissionAction admissionsAction;

	@In(create = true, value = "prescriptionAction")
	com.nas.recovery.web.action.patient.PrescriptionAction prescriptionsAction;

	@In(create = true, value = "unusualOccurenceAction")
	com.nas.recovery.web.action.unusualoccurences.UnusualOccurenceAction unusualOccurencesAction;

	@In(create = true, value = "documentAction")
	com.nas.recovery.web.action.patient.DocumentAction documentsAction;

	@In(create = true, value = "allergyAction")
	com.nas.recovery.web.action.patient.AllergyAction allergysAction;

	@In(create = true, value = "immunizationAction")
	com.nas.recovery.web.action.patient.ImmunizationAction immunizationsAction;

	@DataModel
	private List<Patient> patientRecordList;

	public void setPatientId(Long id) {
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
	public void setPatientIdForModalDlg(Long id) {
		setId(id);
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
		return new Patient();
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

		com.oreon.callosum.patient.Admission admission = (com.oreon.callosum.patient.Admission) org.jboss.seam.Component
				.getInstance("admission");
		admission.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedAdmission");

		com.oreon.callosum.patient.Prescription prescription = (com.oreon.callosum.patient.Prescription) org.jboss.seam.Component
				.getInstance("prescription");
		prescription.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedPrescription");

		com.oreon.callosum.unusualoccurences.UnusualOccurence unusualOccurence = (com.oreon.callosum.unusualoccurences.UnusualOccurence) org.jboss.seam.Component
				.getInstance("unusualOccurence");
		unusualOccurence.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedUnusualOccurence");

		com.oreon.callosum.patient.Document document = (com.oreon.callosum.patient.Document) org.jboss.seam.Component
				.getInstance("document");
		document.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedDocument");

		com.oreon.callosum.patient.Allergy allergy = (com.oreon.callosum.patient.Allergy) org.jboss.seam.Component
				.getInstance("allergy");
		allergy.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedAllergy");

		com.oreon.callosum.patient.Immunization immunization = (com.oreon.callosum.patient.Immunization) org.jboss.seam.Component
				.getInstance("immunization");
		immunization.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedImmunization");

	}

	protected List<com.oreon.callosum.patient.Admission> listAdmissions;

	void initListAdmissions() {
		listAdmissions = new ArrayList<com.oreon.callosum.patient.Admission>();

		if (getInstance().getAdmissions().isEmpty()) {

		} else
			listAdmissions.addAll(getInstance().getAdmissions());

	}

	public List<com.oreon.callosum.patient.Admission> getListAdmissions() {
		if (listAdmissions == null)
			initListAdmissions();
		return listAdmissions;
	}

	public void setListAdmissions(
			List<com.oreon.callosum.patient.Admission> listAdmissions) {
		this.listAdmissions = listAdmissions;
	}

	public void deleteAdmissions(int index) {
		listAdmissions.remove(index);
	}

	@Begin(join = true)
	public void addAdmissions() {
		Admission admissions = new Admission();

		admissions.setPatient(getInstance());

		getListAdmissions().add(admissions);
	}

	protected List<com.oreon.callosum.patient.Prescription> listPrescriptions;

	void initListPrescriptions() {
		listPrescriptions = new ArrayList<com.oreon.callosum.patient.Prescription>();

		if (getInstance().getPrescriptions().isEmpty()) {

		} else
			listPrescriptions.addAll(getInstance().getPrescriptions());

	}

	public List<com.oreon.callosum.patient.Prescription> getListPrescriptions() {
		if (listPrescriptions == null)
			initListPrescriptions();
		return listPrescriptions;
	}

	public void setListPrescriptions(
			List<com.oreon.callosum.patient.Prescription> listPrescriptions) {
		this.listPrescriptions = listPrescriptions;
	}

	public void deletePrescriptions(int index) {
		listPrescriptions.remove(index);
	}

	@Begin(join = true)
	public void addPrescriptions() {
		Prescription prescriptions = new Prescription();

		prescriptions.setPatient(getInstance());

		getListPrescriptions().add(prescriptions);
	}

	protected List<com.oreon.callosum.unusualoccurences.UnusualOccurence> listUnusualOccurences;

	void initListUnusualOccurences() {
		listUnusualOccurences = new ArrayList<com.oreon.callosum.unusualoccurences.UnusualOccurence>();

		if (getInstance().getUnusualOccurences().isEmpty()) {

		} else
			listUnusualOccurences.addAll(getInstance().getUnusualOccurences());

	}

	public List<com.oreon.callosum.unusualoccurences.UnusualOccurence> getListUnusualOccurences() {
		if (listUnusualOccurences == null)
			initListUnusualOccurences();
		return listUnusualOccurences;
	}

	public void setListUnusualOccurences(
			List<com.oreon.callosum.unusualoccurences.UnusualOccurence> listUnusualOccurences) {
		this.listUnusualOccurences = listUnusualOccurences;
	}

	public void deleteUnusualOccurences(int index) {
		listUnusualOccurences.remove(index);
	}

	@Begin(join = true)
	public void addUnusualOccurences() {
		UnusualOccurence unusualOccurences = new UnusualOccurence();

		unusualOccurences.setPatient(getInstance());

		getListUnusualOccurences().add(unusualOccurences);
	}

	protected List<com.oreon.callosum.patient.Document> listDocuments;

	void initListDocuments() {
		listDocuments = new ArrayList<com.oreon.callosum.patient.Document>();

		if (getInstance().getDocuments().isEmpty()) {

		} else
			listDocuments.addAll(getInstance().getDocuments());

	}

	public List<com.oreon.callosum.patient.Document> getListDocuments() {
		if (listDocuments == null)
			initListDocuments();
		return listDocuments;
	}

	public void setListDocuments(
			List<com.oreon.callosum.patient.Document> listDocuments) {
		this.listDocuments = listDocuments;
	}

	public void deleteDocuments(int index) {
		listDocuments.remove(index);
	}

	@Begin(join = true)
	public void addDocuments() {
		Document documents = new Document();

		documents.setPatient(getInstance());

		getListDocuments().add(documents);
	}

	protected List<com.oreon.callosum.patient.Allergy> listAllergys;

	void initListAllergys() {
		listAllergys = new ArrayList<com.oreon.callosum.patient.Allergy>();

		if (getInstance().getAllergys().isEmpty()) {

		} else
			listAllergys.addAll(getInstance().getAllergys());

	}

	public List<com.oreon.callosum.patient.Allergy> getListAllergys() {
		if (listAllergys == null)
			initListAllergys();
		return listAllergys;
	}

	public void setListAllergys(
			List<com.oreon.callosum.patient.Allergy> listAllergys) {
		this.listAllergys = listAllergys;
	}

	public void deleteAllergys(int index) {
		listAllergys.remove(index);
	}

	@Begin(join = true)
	public void addAllergys() {
		Allergy allergys = new Allergy();

		allergys.setPatient(getInstance());

		getListAllergys().add(allergys);
	}

	protected List<com.oreon.callosum.patient.Immunization> listImmunizations;

	void initListImmunizations() {
		listImmunizations = new ArrayList<com.oreon.callosum.patient.Immunization>();

		if (getInstance().getImmunizations().isEmpty()) {

		} else
			listImmunizations.addAll(getInstance().getImmunizations());

	}

	public List<com.oreon.callosum.patient.Immunization> getListImmunizations() {
		if (listImmunizations == null)
			initListImmunizations();
		return listImmunizations;
	}

	public void setListImmunizations(
			List<com.oreon.callosum.patient.Immunization> listImmunizations) {
		this.listImmunizations = listImmunizations;
	}

	public void deleteImmunizations(int index) {
		listImmunizations.remove(index);
	}

	@Begin(join = true)
	public void addImmunizations() {
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

}
