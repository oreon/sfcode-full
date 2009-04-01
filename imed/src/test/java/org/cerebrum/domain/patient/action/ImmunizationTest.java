package org.cerebrum.domain.patient.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ImmunizationTest extends org.witchcraft.action.test.BaseTest {

	ImmunizationAction immunizationAction = new ImmunizationAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		immunizationAction.setEntityManager(em);
	}

}
