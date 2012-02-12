package com.hrb.tservices.web.action.faq;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.faq.FaqCategory;

public class FaqCategoryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FaqCategory> {

	FaqCategoryAction faqCategoryAction = new FaqCategoryAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FaqCategory> getAction() {
		return faqCategoryAction;
	}

}
