package org.cerebrum.domain.provider.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class NurseTest extends org.witchcraft.action.test.BaseTest {

	NurseAction nurseAction = new NurseAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		nurseAction.setEntityManager(em);
	}

}
