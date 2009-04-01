package org.cerebrum.domain.prescription.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class PrescriptionTest extends org.witchcraft.action.test.BaseTest {

	PrescriptionAction prescriptionAction = new PrescriptionAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		prescriptionAction.setEntityManager(em);
	}

}
