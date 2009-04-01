package org.cerebrum.domain.prescription.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ItemTest extends org.witchcraft.action.test.BaseTest {

	ItemAction itemAction = new ItemAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		itemAction.setEntityManager(em);
	}

}
