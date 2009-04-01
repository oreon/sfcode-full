package org.cerebrum.domain.diagnostics.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class DxTestTest extends org.witchcraft.action.test.BaseTest {

	DxTestAction dxTestAction = new DxTestAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		dxTestAction.setEntityManager(em);
	}

}
