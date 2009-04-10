package org.cerebrum.domain.patient.action;

import java.util.List;

import org.cerebrum.domain.patient.Patient;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;

public class PatientTest extends org.witchcraft.action.test.BaseTest<Patient> {

	PatientAction patientAction = new PatientAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Patient> getAction() {
		return patientAction;
	}
	
	@Test
	public void indexTest(){
		patientAction.setEntity(new Patient());
		patientAction.reIndex();
	}
	
	@Test
	public void textSearchTest(){
		patientAction.setQueryString("ajax");
		patientAction.textSearch();
		List<Patient> patients = patientAction.getEntityList();
		System.out.println(patients.size());
	}
}
