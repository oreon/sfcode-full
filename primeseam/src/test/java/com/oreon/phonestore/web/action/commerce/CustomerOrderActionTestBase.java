package com.oreon.phonestore.web.action.commerce;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.CustomerOrder;

public class CustomerOrderActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<CustomerOrder> {

	CustomerOrderAction customerOrderAction = new CustomerOrderAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<CustomerOrder> getAction() {
		return customerOrderAction;
	}

}
