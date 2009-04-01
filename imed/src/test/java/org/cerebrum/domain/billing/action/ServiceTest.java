package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ServiceTest extends org.witchcraft.action.test.BaseTest {

	ServiceAction serviceAction = new ServiceAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		serviceAction.setEntityManager(em);
	}

}
