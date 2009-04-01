package org.cerebrum.domain.demographics.action;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

public class CountryTest extends org.witchcraft.action.test.BaseTest {

	CountryAction countryAction = new CountryAction();

	@BeforeClass
	public void init() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		countryAction.setEntityManager(em);
	}

}
