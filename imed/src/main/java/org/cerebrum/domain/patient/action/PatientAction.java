package org.cerebrum.domain.patient.action;

import org.cerebrum.domain.patient.Patient;
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

import org.witchcraft.base.entity.EntityTemplate;
import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

@Scope(ScopeType.CONVERSATION)
@Name("patientAction")
public class PatientAction extends BaseAction<Patient>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Patient patient;

	@DataModel
	private List<Patient> patientList;
	
	

	@Factory("patientList")
	@Observer("archivedPatient")
	public void findRecords() {
		search();
	}

	public Patient getEntity() {
		return patient;
	}

	@Override
	public void setEntity(Patient t) {
		this.patient = t;
	}

	@Override
	public void setEntityList(List<Patient> list) {
		this.patientList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (patient.getPrimaryPhysician() != null) {
			criteria = criteria.add(Restrictions.eq("primaryPhysician.id",
					patient.getPrimaryPhysician().getId()));
		}

	}
	
	

	public void updateAssociations() {

		org.cerebrum.domain.patient.Allergy allergy = (org.cerebrum.domain.patient.Allergy) Component
				.getInstance("allergy");
		allergy.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedAllergy");

		org.cerebrum.domain.patient.Immunization immunization = (org.cerebrum.domain.patient.Immunization) Component
				.getInstance("immunization");
		immunization.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedImmunization");

		org.cerebrum.domain.prescription.Prescription prescription = (org.cerebrum.domain.prescription.Prescription) Component
				.getInstance("prescription");
		prescription.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedPrescription");

		org.cerebrum.domain.patient.Document document = (org.cerebrum.domain.patient.Document) Component
				.getInstance("document");
		document.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedDocument");

		org.cerebrum.domain.encounter.Encounter encounter = (org.cerebrum.domain.encounter.Encounter) Component
				.getInstance("encounter");
		encounter.setPatient(patient);
		events.raiseTransactionSuccessEvent("archivedEncounter");

	}

	public List<Patient> getEntityList() {
		if (patientList == null) {
			findRecords();
		}
		return patientList;
	}

}
