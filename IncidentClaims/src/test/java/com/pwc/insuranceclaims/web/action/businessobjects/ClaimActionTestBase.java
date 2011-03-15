package com.pwc.insuranceclaims.web.action.businessobjects;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.pwc.insuranceclaims.quickclaim.Claim;
import com.pwc.insuranceclaims.web.action.quickclaim.ClaimAction;

public class ClaimActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Claim> {

	ClaimAction claimAction = new ClaimAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Claim> getAction() {
		return claimAction;
	}

}
