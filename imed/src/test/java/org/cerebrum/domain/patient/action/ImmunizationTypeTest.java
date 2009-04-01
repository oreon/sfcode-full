package org.cerebrum.domain.patient.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ImmunizationTypeTest extends org.witchcraft.action.test.BaseTest {

	ImmunizationTypeAction immunizationTypeAction = new ImmunizationTypeAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		immunizationTypeAction.setEntityManager(em);
	}

}
