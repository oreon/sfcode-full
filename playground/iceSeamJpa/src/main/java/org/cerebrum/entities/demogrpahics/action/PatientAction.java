package org.cerebrum.entities.demogrpahics.action;

import java.util.List;

import org.cerebrum.entities.demogrpahics.Patient;
import org.cerebrum.seam.action.base.BaseAction;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

@Scope(ScopeType.CONVERSATION)
@Name("patientAction")
public class PatientAction extends BaseAction {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Patient patient;

	@DataModel
	private List<Patient> patientList;

	@Factory("patientList")
	public void findStduents() {
		patientList = entityManager
				.createQuery(
						"select patient from Patient patient order by patient.lastName")
				.getResultList();
	}

	@Begin
	public String select(Patient patient) {
		this.patient = entityManager.merge(patient);
		log
				.info("User selected Patient: #{patient.displayName} #{patient.id} #{patient.firstName}");
		return "/editPatient.jspx";
	}

	@End
	public String save() {
		facesMessages.add("Successfully saved  #{patient.displayName}");
		entityManager.persist(patient);
		return "/listPatient.jspx";
	}

}
