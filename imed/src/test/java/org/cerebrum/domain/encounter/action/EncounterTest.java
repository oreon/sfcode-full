package org.cerebrum.domain.encounter.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class EncounterTest extends org.witchcraft.action.test.BaseTest {

	EncounterAction encounterAction = new EncounterAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		encounterAction.setEntityManager(em);
	}

}
