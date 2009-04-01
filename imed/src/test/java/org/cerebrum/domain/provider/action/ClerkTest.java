package org.cerebrum.domain.provider.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ClerkTest extends org.witchcraft.action.test.BaseTest {

	ClerkAction clerkAction = new ClerkAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		clerkAction.setEntityManager(em);
	}

}
