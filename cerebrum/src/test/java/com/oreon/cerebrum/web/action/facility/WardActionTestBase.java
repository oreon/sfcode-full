package com.oreon.cerebrum.web.action.facility;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.facility.Ward;

public class WardActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Ward> {

	WardAction wardAction = new WardAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Ward> getAction() {
		return wardAction;
	}

}
