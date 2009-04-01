package org.cerebrum.domain.facility.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class WardTest extends org.witchcraft.action.test.BaseTest {

	WardAction wardAction = new WardAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		wardAction.setEntityManager(em);
	}

}
