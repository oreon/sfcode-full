package org.cerebrum.domain.patient.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class DocumentTest extends org.witchcraft.action.test.BaseTest {

	DocumentAction documentAction = new DocumentAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		documentAction.setEntityManager(em);
	}

}
