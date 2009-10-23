package com.shan.customermgt.domain.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.shan.customermgt.domain.Account;

public class AccountTestBase
		extends
			org.witchcraft.action.test.BaseTest<Account> {

	AccountAction accountAction = new AccountAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Account> getAction() {
		return accountAction;
	}

}
