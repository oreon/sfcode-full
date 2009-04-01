package org.cerebrum.domain.facility.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class BedTest extends org.witchcraft.action.test.BaseTest {

	BedAction bedAction = new BedAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		bedAction.setEntityManager(em);
	}

}
