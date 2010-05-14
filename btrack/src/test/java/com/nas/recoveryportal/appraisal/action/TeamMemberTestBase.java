package com.nas.recoveryportal.appraisal.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recoveryportal.appraisal.TeamMember;

public class TeamMemberTestBase
		extends
			org.witchcraft.action.test.BaseTest<TeamMember> {

	TeamMemberAction teamMemberAction = new TeamMemberAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<TeamMember> getAction() {
		return teamMemberAction;
	}

}
