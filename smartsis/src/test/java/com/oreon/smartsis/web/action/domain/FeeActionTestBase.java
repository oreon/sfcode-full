package com.oreon.smartsis.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.domain.Fee;

public class FeeActionTestBase extends org.witchcraft.action.test.BaseTest<Fee> {

	FeeAction feeAction = new FeeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Fee> getAction() {
		return feeAction;
	}

}
