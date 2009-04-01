package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class PremiumTest extends org.witchcraft.action.test.BaseTest {

	PremiumAction premiumAction = new PremiumAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		premiumAction.setEntityManager(em);
	}

}
