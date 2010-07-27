package com.nas.recovery.web.action.realestate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.realestate.TenantInfo;

public class TenantInfoActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<TenantInfo> {

	TenantInfoAction tenantInfoAction = new TenantInfoAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<TenantInfo> getAction() {
		return tenantInfoAction;
	}

}
