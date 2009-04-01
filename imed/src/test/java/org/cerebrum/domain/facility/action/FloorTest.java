package org.cerebrum.domain.facility.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class FloorTest extends org.witchcraft.action.test.BaseTest {

	FloorAction floorAction = new FloorAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		floorAction.setEntityManager(em);
	}

}
