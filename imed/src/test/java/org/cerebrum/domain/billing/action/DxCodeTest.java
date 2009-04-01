package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class DxCodeTest extends org.witchcraft.action.test.BaseTest {

	DxCodeAction dxCodeAction = new DxCodeAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		dxCodeAction.setEntityManager(em);
	}

}
