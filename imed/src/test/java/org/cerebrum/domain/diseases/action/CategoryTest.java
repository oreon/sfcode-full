package org.cerebrum.domain.diseases.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class CategoryTest extends org.witchcraft.action.test.BaseTest {

	CategoryAction categoryAction = new CategoryAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		categoryAction.setEntityManager(em);
	}

}
