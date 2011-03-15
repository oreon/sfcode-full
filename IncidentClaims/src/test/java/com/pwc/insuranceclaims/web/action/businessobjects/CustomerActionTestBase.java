package com.pwc.insuranceclaims.web.action.businessobjects;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.pwc.insuranceclaims.quickclaim.Customer;
import com.pwc.insuranceclaims.web.action.quickclaim.CustomerAction;

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
