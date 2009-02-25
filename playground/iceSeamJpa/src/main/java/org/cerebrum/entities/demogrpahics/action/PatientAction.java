package org.cerebrum.entities.demogrpahics.action;

import java.util.List;

import org.cerebrum.entities.demogrpahics.Patient;
import org.cerebrum.seam.action.base.BaseAction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
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

	@In(create = true, required = false)
	@Out(required = false)
	@DataModelSelection
	private Patient patient;

	@DataModel
	private List<Patient> patientList;

	@Factory("patientList")
	public void findPatients() {
		patientList = entityManager
				.createQuery(
						"select patient from Patient patient order by patient.lastName")
				.getResultList();
	}

	@Begin(join = true)
	public String select(Patient patient) {
		if(patient.getId() != null)
			this.patient = entityManager.merge(patient);
		//log.info("User selected Patient: #{patient.displayName} #{patient.id} #{patient.firstName}");
		return "/patient/editPatient.jspx";
	}

	@End
	public String save() {
		entityManager.persist(patient);
		facesMessages.add("Successfully saved  #{patient.displayName}");
		events.raiseTransactionSuccessEvent("patientSaved");
		return "/patient/listPatient.jspx";
	}
	
	@End
	public String cancel(){
		return "/patient/listPatient.jspx";
	}
	
	@End
	public String createNew(){
		patient = new Patient();
		return "/patient/editPatient.jspx";
	}

	public void search(){
		Criteria criteria = createExampleCriteria();
		patientList = criteria.list();
	}

	public Criteria createExampleCriteria() {
		Session session = (Session) entityManager.getDelegate();

		Example example = Example.create(patient).enableLike(
				MatchMode.START).ignoreCase().excludeZeroes();

		Criteria criteria = session.createCriteria(patient.getClass()).add(example);
		/*
		for (String exclude : excludedProperties) {
			example.excludeProperty(exclude);
		}*/
		return criteria;
	}

}
