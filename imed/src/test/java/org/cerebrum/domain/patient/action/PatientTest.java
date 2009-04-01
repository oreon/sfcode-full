package org.cerebrum.domain.patient.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class PatientTest extends org.witchcraft.action.test.BaseTest {

	PatientAction patientAction = new PatientAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		patientAction.setEntityManager(em);
	}

}
