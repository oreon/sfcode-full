package com.nas.recovery.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.tapovan.domain.GradeFee;

public class GradeFeeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<GradeFee> {

	GradeFeeAction gradeFeeAction = new GradeFeeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<GradeFee> getAction() {
		return gradeFeeAction;
	}

}
