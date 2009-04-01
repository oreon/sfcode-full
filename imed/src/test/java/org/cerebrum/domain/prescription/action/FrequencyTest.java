package org.cerebrum.domain.prescription.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class FrequencyTest extends org.witchcraft.action.test.BaseTest {

	FrequencyAction frequencyAction = new FrequencyAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		frequencyAction.setEntityManager(em);
	}

}
