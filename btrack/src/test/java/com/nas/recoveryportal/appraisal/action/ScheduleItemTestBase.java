package com.nas.recoveryportal.appraisal.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recoveryportal.appraisal.ScheduleItem;

public class ScheduleItemTestBase
		extends
			org.witchcraft.action.test.BaseTest<ScheduleItem> {

	ScheduleItemAction scheduleItemAction = new ScheduleItemAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ScheduleItem> getAction() {
		return scheduleItemAction;
	}

}
