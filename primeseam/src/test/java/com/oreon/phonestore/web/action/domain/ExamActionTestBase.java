package com.oreon.phonestore.web.action.domain;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Exam;

public class ExamActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Exam> {

	ExamAction examAction = new ExamAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Exam> getAction() {
		return examAction;
	}

}
