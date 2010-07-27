package com.nas.recovery.web.action.loan;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.loan.Underwriter;

public class UnderwriterActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Underwriter> {

	UnderwriterAction underwriterAction = new UnderwriterAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Underwriter> getAction() {
		return underwriterAction;
	}

}
