package org.cerebrum.domain.provider.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class TechnicianTest extends org.witchcraft.action.test.BaseTest {

	TechnicianAction technicianAction = new TechnicianAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		technicianAction.setEntityManager(em);
	}

}
