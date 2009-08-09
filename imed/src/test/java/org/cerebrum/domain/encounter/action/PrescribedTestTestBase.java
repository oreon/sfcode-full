package org.cerebrum.domain.encounter.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.encounter.PrescribedTest;

public class PrescribedTestTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescribedTest> {

	PrescribedTestAction prescribedTestAction = new PrescribedTestAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PrescribedTest> getAction() {
		return prescribedTestAction;
	}

}
