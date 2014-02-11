package com.oreon.phonestore.web.action.domain;

import org.junit.Before;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Question;

public class QuestionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Question> {

	QuestionAction questionAction = new QuestionAction();

	@Before
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Question> getAction() {
		return questionAction;
	}

}
