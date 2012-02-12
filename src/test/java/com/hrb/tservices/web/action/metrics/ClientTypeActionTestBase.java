package com.hrb.tservices.web.action.metrics;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.metrics.ClientType;

public class ClientTypeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ClientType> {

	ClientTypeAction clientTypeAction = new ClientTypeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ClientType> getAction() {
		return clientTypeAction;
	}

}
