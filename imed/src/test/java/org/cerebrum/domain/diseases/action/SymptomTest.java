package org.cerebrum.domain.diseases.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class SymptomTest extends org.witchcraft.action.test.BaseTest {

	SymptomAction symptomAction = new SymptomAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		symptomAction.setEntityManager(em);
	}

}
