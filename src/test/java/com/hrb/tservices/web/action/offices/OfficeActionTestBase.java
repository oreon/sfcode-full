package com.hrb.tservices.web.action.offices;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.offices.Office;

public class OfficeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Office> {

	OfficeAction officeAction = new OfficeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Office> getAction() {
		return officeAction;
	}

}
