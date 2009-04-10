package org.cerebrum.domain.demographics.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.demographics.Country;

public class CountryTest extends org.witchcraft.action.test.BaseTest<Country> {

	CountryAction countryAction = new CountryAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Country> getAction() {
		return countryAction;
	}
}
