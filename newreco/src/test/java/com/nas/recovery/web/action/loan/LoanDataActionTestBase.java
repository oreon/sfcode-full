package com.nas.recovery.web.action.loan;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.loan.LoanData;

public class LoanDataActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<LoanData> {

	LoanDataAction loanDataAction = new LoanDataAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<LoanData> getAction() {
		return loanDataAction;
	}

}
