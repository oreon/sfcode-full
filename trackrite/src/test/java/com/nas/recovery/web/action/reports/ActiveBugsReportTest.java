package com.nas.recovery.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class ActiveBugsReportTest extends BaseReportsTest {

	@Test
	public void testActiveBugsReportReport() {
		try {
			runReportTest("ActiveBugsReport");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
