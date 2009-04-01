package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class ProcedureCodeTest extends org.witchcraft.action.test.BaseTest {

	ProcedureCodeAction procedureCodeAction = new ProcedureCodeAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		procedureCodeAction.setEntityManager(em);
	}

}
