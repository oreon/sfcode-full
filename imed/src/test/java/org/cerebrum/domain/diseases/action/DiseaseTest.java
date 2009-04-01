package org.cerebrum.domain.diseases.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class DiseaseTest extends org.witchcraft.action.test.BaseTest {

	DiseaseAction diseaseAction = new DiseaseAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		diseaseAction.setEntityManager(em);
	}

}
