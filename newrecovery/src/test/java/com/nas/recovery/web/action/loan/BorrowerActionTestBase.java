package com.nas.recovery.web.action.loan;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.loan.Borrower;

public class BorrowerActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Borrower> {

	BorrowerAction borrowerAction = new BorrowerAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Borrower> getAction() {
		return borrowerAction;
	}

}
