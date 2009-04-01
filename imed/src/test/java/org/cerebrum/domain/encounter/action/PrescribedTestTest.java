package org.cerebrum.domain.encounter.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class PrescribedTestTest extends org.witchcraft.action.test.BaseTest {

	PrescribedTestAction prescribedTestAction = new PrescribedTestAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		prescribedTestAction.setEntityManager(em);
	}

}
