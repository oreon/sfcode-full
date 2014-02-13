package com.oreon.phonestore.web.action.commerce;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.Customer;

public class CustomerActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Customer> {

	CustomerAction customerAction = new CustomerAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Customer> getAction() {
		return customerAction;
	}

}
