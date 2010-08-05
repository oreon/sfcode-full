package com.nas.recovery.web.action.legal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.legal.Chargee;

public class ChargeeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Chargee> {

	ChargeeAction chargeeAction = new ChargeeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Chargee> getAction() {
		return chargeeAction;
	}

}
