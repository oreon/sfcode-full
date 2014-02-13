package com.oreon.phonestore.web.action.commerce;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.CustomerQuestion;

public class CustomerQuestionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<CustomerQuestion> {

	CustomerQuestionAction customerQuestionAction = new CustomerQuestionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<CustomerQuestion> getAction() {
		return customerQuestionAction;
	}

}
