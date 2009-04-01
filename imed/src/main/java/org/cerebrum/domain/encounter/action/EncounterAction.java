package org.cerebrum.domain.encounter.action;

import org.cerebrum.domain.encounter.Encounter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

import org.cerebrum.domain.encounter.Complaint;
import org.cerebrum.domain.encounter.PrescribedTest;

@Scope(ScopeType.CONVERSATION)
@Name("encounterAction")
public class EncounterAction extends BaseAction<Encounter>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Encounter encounter;

	@DataModel
	private List<Encounter> encounterList;

	@Factory("encounterList")
	@Observer("archivedEncounter")
	public void findRecords() {
		search();
	}

	public Encounter getEntity() {
		return encounter;
	}

	@Override
	public void setEntity(Encounter t) {
		this.encounter = t;
	}

	@Override
	public void setEntityList(List<Encounter> list) {
		this.encounterList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (encounter.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", encounter
					.getPatient().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<Complaint> listComplaints;

	void initListComplaints() {
		listComplaints = new ArrayList<Complaint>();
		if (encounter.getComplaints().isEmpty()) {

		} else
			listComplaints.addAll(encounter.getComplaints());
	}

	public List<Complaint> getListComplaints() {
		if (listComplaints == null) {
			initListComplaints();
		}
		return listComplaints;
	}

	public void setListComplaints(List<Complaint> listComplaints) {
		this.listComplaints = listComplaints;
	}

	public void deleteComplaints(Complaint complaints) {
		listComplaints.remove(complaints);
	}

	@Begin(join = true)
	public void addComplaints() {
		Complaint complaints = new Complaint();

		complaints.setEncounter(encounter);

		listComplaints.add(complaints);
	}

	private List<PrescribedTest> listPrescribedTests;

	void initListPrescribedTests() {
		listPrescribedTests = new ArrayList<PrescribedTest>();
		if (encounter.getPrescribedTests().isEmpty()) {

		} else
			listPrescribedTests.addAll(encounter.getPrescribedTests());
	}

	public List<PrescribedTest> getListPrescribedTests() {
		if (listPrescribedTests == null) {
			initListPrescribedTests();
		}
		return listPrescribedTests;
	}

	public void setListPrescribedTests(List<PrescribedTest> listPrescribedTests) {
		this.listPrescribedTests = listPrescribedTests;
	}

	public void deletePrescribedTests(PrescribedTest prescribedTests) {
		listPrescribedTests.remove(prescribedTests);
	}

	@Begin(join = true)
	public void addPrescribedTests() {
		PrescribedTest prescribedTests = new PrescribedTest();

		prescribedTests.setEncounter(encounter);

		listPrescribedTests.add(prescribedTests);
	}

	public void updateComposedAssociations() {

		encounter.getComplaints().clear();
		encounter.getComplaints().addAll(listComplaints);

		encounter.getPrescribedTests().clear();
		encounter.getPrescribedTests().addAll(listPrescribedTests);

	}

	public List<Encounter> getEntityList() {
		if (encounterList == null) {
			findRecords();
		}
		return encounterList;
	}

}
