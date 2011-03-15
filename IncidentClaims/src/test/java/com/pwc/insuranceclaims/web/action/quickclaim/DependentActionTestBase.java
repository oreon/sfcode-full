package com.pwc.insuranceclaims.web.action.quickclaim;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.pwc.insuranceclaims.quickclaim.Dependent;

public class DependentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Dependent> {

	DependentAction dependentAction = new DependentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Dependent> getAction() {
		return dependentAction;
	}

}
