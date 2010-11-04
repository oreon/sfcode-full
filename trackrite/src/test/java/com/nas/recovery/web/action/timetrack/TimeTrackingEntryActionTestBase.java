package com.nas.recovery.web.action.timetrack;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.timetrack.TimeTrackingEntry;

public class TimeTrackingEntryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<TimeTrackingEntry> {

	TimeTrackingEntryAction timeTrackingEntryAction = new TimeTrackingEntryAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<TimeTrackingEntry> getAction() {
		return timeTrackingEntryAction;
	}

}
