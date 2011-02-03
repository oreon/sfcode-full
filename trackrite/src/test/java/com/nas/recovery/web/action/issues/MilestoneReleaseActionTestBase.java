package com.nas.recovery.web.action.issues;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.issues.MilestoneRelease;

public class MilestoneReleaseActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<MilestoneRelease> {

	MilestoneReleaseAction milestoneReleaseAction = new MilestoneReleaseAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<MilestoneRelease> getAction() {
		return milestoneReleaseAction;
	}

}
