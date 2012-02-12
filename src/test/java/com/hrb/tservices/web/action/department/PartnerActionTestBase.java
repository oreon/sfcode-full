package com.hrb.tservices.web.action.department;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.department.Partner;

public class PartnerActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Partner> {

	PartnerAction partnerAction = new PartnerAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Partner> getAction() {
		return partnerAction;
	}

}
