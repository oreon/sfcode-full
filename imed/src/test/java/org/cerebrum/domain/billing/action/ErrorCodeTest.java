package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ErrorCodeTest extends org.witchcraft.action.test.BaseTest {

	ErrorCodeAction errorCodeAction = new ErrorCodeAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		errorCodeAction.setEntityManager(em);
	}

}
