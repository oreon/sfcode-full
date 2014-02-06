package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.ConditionFinding;

public class ConditionFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ConditionFinding> {

	ConditionFindingAction conditionFindingAction = new ConditionFindingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ConditionFinding> getAction() {
		return conditionFindingAction;
	}

}