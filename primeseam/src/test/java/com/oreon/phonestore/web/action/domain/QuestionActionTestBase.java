package com.oreon.phonestore.web.action.domain;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Question;

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
