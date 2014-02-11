package com.oreon.phonestore.web.action.domain;

import org.junit.Before;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Exam;

public class ExamActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Exam> {

	ExamAction examAction = new ExamAction();

	@Before
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Exam> getAction() {
		return examAction;
	}

}
