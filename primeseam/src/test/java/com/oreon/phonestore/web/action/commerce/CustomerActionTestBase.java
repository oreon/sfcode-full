package com.oreon.phonestore.web.action.commerce;

import org.junit.Before;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.Customer;

public class CustomerActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Customer> {

	CustomerAction customerAction = new CustomerAction();

	@Before
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Customer> getAction() {
		return customerAction;
	}

}
