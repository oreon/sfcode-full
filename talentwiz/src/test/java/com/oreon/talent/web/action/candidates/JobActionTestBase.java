package com.oreon.talent.web.action.candidates;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.talent.candidates.Job;

public class JobActionTestBase extends org.witchcraft.action.test.BaseTest<Job> {

	JobAction jobAction = new JobAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Job> getAction() {
		return jobAction;
	}

	@Test
	public void testApply() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				JobAction jobAction = (JobAction) org.jboss.seam.Component
						.getInstance("jobAction");

				// assert(jobAction.apply()).equals("");
			}

		}.run();
	}

}
