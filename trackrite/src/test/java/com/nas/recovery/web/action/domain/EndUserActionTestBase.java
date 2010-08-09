package com.nas.recovery.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.domain.EndUser;

public class EndUserActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<EndUser> {

	EndUserAction endUserAction = new EndUserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<EndUser> getAction() {
		return endUserAction;
	}

}
