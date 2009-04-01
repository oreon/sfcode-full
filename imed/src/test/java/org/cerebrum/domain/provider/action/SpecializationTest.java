package org.cerebrum.domain.provider.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class SpecializationTest extends org.witchcraft.action.test.BaseTest {

	SpecializationAction specializationAction = new SpecializationAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		specializationAction.setEntityManager(em);
	}

}
