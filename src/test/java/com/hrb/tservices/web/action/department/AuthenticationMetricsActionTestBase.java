package com.hrb.tservices.web.action.department;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.department.AuthenticationMetrics;

public class AuthenticationMetricsActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AuthenticationMetrics> {

	AuthenticationMetricsAction authenticationMetricsAction = new AuthenticationMetricsAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AuthenticationMetrics> getAction() {
		return authenticationMetricsAction;
	}

}
