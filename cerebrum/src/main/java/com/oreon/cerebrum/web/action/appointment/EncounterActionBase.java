package com.oreon.cerebrum.web.action.appointment;

import com.oreon.cerebrum.appointment.Encounter;

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

import com.oreon.cerebrum.appointment.History;
import com.oreon.cerebrum.appointment.PrescribedTest;

public abstract class EncounterActionBase extends BaseAction<Encounter>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Encounter encounter;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "physicianAction")
	com.oreon.cerebrum.web.action.employee.PhysicianAction physicianAction;

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

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
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

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

		com.oreon.cerebrum.employee.Physician physician = physicianAction
				.getDefinedInstance();
		if (physician != null && isNew()) {
			getInstance().setPhysician(physician);
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

		if (encounter.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", encounter
					.getPatient().getId()));
		}

		if (encounter.getPhysician() != null) {
			criteria = criteria.add(Restrictions.eq("physician.id", encounter
					.getPhysician().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (encounter.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

		if (encounter.getPhysician() != null) {
			physicianAction.setInstance(getInstance().getPhysician());
		}

		initListHistorys();

		initListPrescribedTests();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.appointment.History> listHistorys = new ArrayList<com.oreon.cerebrum.appointment.History>();

	void initListHistorys() {

		if (listHistorys.isEmpty())
			listHistorys.addAll(getInstance().getHistorys());

	}

	public List<com.oreon.cerebrum.appointment.History> getListHistorys() {

		prePopulateListHistorys();
		return listHistorys;
	}

	public void prePopulateListHistorys() {
	}

	public void setListHistorys(
			List<com.oreon.cerebrum.appointment.History> listHistorys) {
		this.listHistorys = listHistorys;
	}

	public void deleteHistorys(int index) {
		listHistorys.remove(index);
	}

	@Begin(join = true)
	public void addHistorys() {
		initListHistorys();
		History historys = new History();

		historys.setEncounter(getInstance());

		getListHistorys().add(historys);
	}

	protected List<com.oreon.cerebrum.appointment.PrescribedTest> listPrescribedTests = new ArrayList<com.oreon.cerebrum.appointment.PrescribedTest>();

	void initListPrescribedTests() {

		if (listPrescribedTests.isEmpty())
			listPrescribedTests.addAll(getInstance().getPrescribedTests());

	}

	public List<com.oreon.cerebrum.appointment.PrescribedTest> getListPrescribedTests() {

		prePopulateListPrescribedTests();
		return listPrescribedTests;
	}

	public void prePopulateListPrescribedTests() {
	}

	public void setListPrescribedTests(
			List<com.oreon.cerebrum.appointment.PrescribedTest> listPrescribedTests) {
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

	public void updateComposedAssociations() {

		if (listHistorys != null) {
			getInstance().getHistorys().clear();
			getInstance().getHistorys().addAll(listHistorys);
		}

		if (listPrescribedTests != null) {
			getInstance().getPrescribedTests().clear();
			getInstance().getPrescribedTests().addAll(listPrescribedTests);
		}
	}

	public void clearLists() {
		listHistorys.clear();
		listPrescribedTests.clear();

	}

	public String viewEncounter() {
		load(currentEntityId);
		return "viewEncounter";
	}

}
