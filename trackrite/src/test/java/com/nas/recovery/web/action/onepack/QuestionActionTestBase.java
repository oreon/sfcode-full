package com.nas.recovery.web.action.onepack;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.onepack.Question;

public class QuestionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Question> {

	QuestionAction questionAction = new QuestionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Question> getAction() {
		return questionAction;
	}

}
