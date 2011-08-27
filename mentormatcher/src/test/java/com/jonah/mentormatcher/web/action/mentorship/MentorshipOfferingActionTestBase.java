package com.jonah.mentormatcher.web.action.mentorship;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.jonah.mentormatcher.domain.mentorship.MentorshipOffering;

public class MentorshipOfferingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<MentorshipOffering> {

	MentorshipOfferingAction mentorshipOfferingAction = new MentorshipOfferingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<MentorshipOffering> getAction() {
		return mentorshipOfferingAction;
	}

	@Test
	public void testCreateOffering() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				MentorshipOfferingAction mentorshipOfferingAction = (MentorshipOfferingAction) org.jboss.seam.Component
						.getInstance("mentorshipOfferingAction");

				// assert(mentorshipOfferingAction.createOffering()).equals("");
			}

		}.run();
	}

	@Test
	public void testApplyForMentorship() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				MentorshipOfferingAction mentorshipOfferingAction = (MentorshipOfferingAction) org.jboss.seam.Component
						.getInstance("mentorshipOfferingAction");

				// assert(mentorshipOfferingAction.applyForMentorship()).equals("");
			}

		}.run();
	}

}
