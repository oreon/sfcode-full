package org.cerebrum.domain.encounter.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ComplaintTest extends org.witchcraft.action.test.BaseTest {

	ComplaintAction complaintAction = new ComplaintAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		complaintAction.setEntityManager(em);
	}

}
