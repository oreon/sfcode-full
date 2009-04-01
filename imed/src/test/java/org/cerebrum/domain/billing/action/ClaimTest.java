package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ClaimTest extends org.witchcraft.action.test.BaseTest {

	ClaimAction claimAction = new ClaimAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		claimAction.setEntityManager(em);
	}

}
