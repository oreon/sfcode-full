package org.cerebrum.domain.provider.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class PhysicianTest extends org.witchcraft.action.test.BaseTest {

	PhysicianAction physicianAction = new PhysicianAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		physicianAction.setEntityManager(em);
	}

}
