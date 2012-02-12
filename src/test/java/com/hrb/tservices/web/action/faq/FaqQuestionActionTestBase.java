package com.hrb.tservices.web.action.faq;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.faq.FaqQuestion;

public class FaqQuestionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FaqQuestion> {

	FaqQuestionAction faqQuestionAction = new FaqQuestionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FaqQuestion> getAction() {
		return faqQuestionAction;
	}

}
