package com.pwc.insuranceclaims.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class ClaimsByStatusTest extends BaseReportsTest {

	@Test
	public void testClaimsByStatusReport() {
		try {
			runReportTest("ClaimsByStatus");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
