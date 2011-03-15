package com.pwc.insuranceclaims.web.action.businessobjects;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.pwc.insuranceclaims.quickclaim.Policy;
import com.pwc.insuranceclaims.web.action.quickclaim.PolicyAction;

public class PolicyActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Policy> {

	PolicyAction policyAction = new PolicyAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Policy> getAction() {
		return policyAction;
	}

}
