package org.cerebrum.domain.diseases.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class CauseTest extends org.witchcraft.action.test.BaseTest {

	CauseAction causeAction = new CauseAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		causeAction.setEntityManager(em);
	}

}
