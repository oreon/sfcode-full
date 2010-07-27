package com.nas.recovery.web.action.legal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.legal.Lawyer;

public class LawyerActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Lawyer> {

	LawyerAction lawyerAction = new LawyerAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Lawyer> getAction() {
		return lawyerAction;
	}

}
