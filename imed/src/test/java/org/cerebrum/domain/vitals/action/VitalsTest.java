package org.cerebrum.domain.vitals.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class VitalsTest extends org.witchcraft.action.test.BaseTest {

	VitalsAction vitalsAction = new VitalsAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		vitalsAction.setEntityManager(em);
	}

}
