package com.nas.recovery.web.action.legal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.legal.Bankruptcy;

public class BankruptcyActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Bankruptcy> {

	BankruptcyAction bankruptcyAction = new BankruptcyAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Bankruptcy> getAction() {
		return bankruptcyAction;
	}

}
