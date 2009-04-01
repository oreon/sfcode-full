package org.cerebrum.domain.patient.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class AllergyTest extends org.witchcraft.action.test.BaseTest {

	AllergyAction allergyAction = new AllergyAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		allergyAction.setEntityManager(em);
	}

}
