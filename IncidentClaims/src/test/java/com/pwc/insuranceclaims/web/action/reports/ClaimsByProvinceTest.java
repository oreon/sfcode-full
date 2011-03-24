package com.pwc.insuranceclaims.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class ClaimsByProvinceTest extends BaseReportsTest {

	@Test
	public void testClaimsByProvinceReport() {
		try {
			runReportTest("ClaimsByProvince");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
