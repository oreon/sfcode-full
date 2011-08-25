package com.jonah.mentormatcher.web.action.mentorship;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.jonah.mentormatcher.domain.mentorship.Testimonial;

public class TestimonialActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Testimonial> {

	TestimonialAction testimonialAction = new TestimonialAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Testimonial> getAction() {
		return testimonialAction;
	}

}
