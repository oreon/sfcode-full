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

import org.cerebrum.domain.patient.Allergy;
import org.cerebrum.domain.patient.Immunization;
import org.cerebrum.domain.vitals.Vitals;

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
	public void findRecords() {
		patientList = entityManager.createQuery(
				"select patient from Patient patient order by patient.id desc")
				.getResultList();
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

	private List<Allergy> listAllergies;

	void initListAllergies() {
		listAllergies = new ArrayList<Allergy>();
		if (patient.getAllergies().isEmpty()) {

		} else
			listAllergies.addAll(patient.getAllergies());
	}

	public List<Allergy> getListAllergies() {
		if (listAllergies == null) {
			initListAllergies();
		}
		return listAllergies;
	}

	public void setListAllergies(List<Allergy> listAllergies) {
		this.listAllergies = listAllergies;
	}

	public void deleteAllergies(Allergy allergies) {
		listAllergies.remove(allergies);
	}

	@Begin(join = true)
	public void addAllergies() {
		Allergy allergies = new Allergy();

		allergies.setPatient(patient);

		listAllergies.add(allergies);
	}

	private List<Immunization> listImmunizations;

	void initListImmunizations() {
		listImmunizations = new ArrayList<Immunization>();
		if (patient.getImmunizations().isEmpty()) {

		} else
			listImmunizations.addAll(patient.getImmunizations());
	}

	public List<Immunization> getListImmunizations() {
		if (listImmunizations == null) {
			initListImmunizations();
		}
		return listImmunizations;
	}

	public void setListImmunizations(List<Immunization> listImmunizations) {
		this.listImmunizations = listImmunizations;
	}

	public void deleteImmunizations(Immunization immunizations) {
		listImmunizations.remove(immunizations);
	}

	@Begin(join = true)
	public void addImmunizations() {
		Immunization immunizations = new Immunization();

		immunizations.setPatient(patient);

		listImmunizations.add(immunizations);
	}

	private List<Vitals> listVitalses;

	void initListVitalses() {
		listVitalses = new ArrayList<Vitals>();
		if (patient.getVitalses().isEmpty()) {

		} else
			listVitalses.addAll(patient.getVitalses());
	}

	public List<Vitals> getListVitalses() {
		if (listVitalses == null) {
			initListVitalses();
		}
		return listVitalses;
	}

	public void setListVitalses(List<Vitals> listVitalses) {
		this.listVitalses = listVitalses;
	}

	public void deleteVitalses(Vitals vitalses) {
		listVitalses.remove(vitalses);
	}

	@Begin(join = true)
	public void addVitalses() {
		Vitals vitalses = new Vitals();

		vitalses.setPatient(patient);

		listVitalses.add(vitalses);
	}

	public void updateComposedAssociations() {

		patient.getAllergies().clear();
		patient.getAllergies().addAll(listAllergies);

		patient.getImmunizations().clear();
		patient.getImmunizations().addAll(listImmunizations);

		patient.getVitalses().clear();
		patient.getVitalses().addAll(listVitalses);

	}

}
