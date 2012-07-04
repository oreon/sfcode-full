package com.pcas.datapkg.web.action.dashboards;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.pcas.datapkg.dashboards.Dashboard;

public class DashboardActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Dashboard> {

	DashboardAction dashboardAction = new DashboardAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Dashboard> getAction() {
		return dashboardAction;
	}

}
