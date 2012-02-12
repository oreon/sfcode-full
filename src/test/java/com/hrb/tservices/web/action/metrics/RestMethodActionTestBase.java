package com.hrb.tservices.web.action.metrics;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.metrics.RestMethod;

public class RestMethodActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<RestMethod> {

	RestMethodAction restMethodAction = new RestMethodAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<RestMethod> getAction() {
		return restMethodAction;
	}

}
