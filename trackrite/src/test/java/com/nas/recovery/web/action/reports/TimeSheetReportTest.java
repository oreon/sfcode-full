package com.nas.recovery.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class TimeSheetReportTest extends BaseReportsTest {

	@Test
	public void testTimeSheetReportReport() {
		try {
			runReportTest("TimeSheetReport");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
