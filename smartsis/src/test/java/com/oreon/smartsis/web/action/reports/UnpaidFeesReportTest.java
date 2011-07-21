package com.oreon.smartsis.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.smartsis.web.action.reports.UnpaidFeesReportAction;

public class UnpaidFeesReportTest extends BaseReportsTest {

	@Test
	public void testUnpaidFeesReportReport() {
		try {
			runReportTest("UnpaidFeesReport");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testUnpaidFeesReportReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				UnpaidFeesReportAction unpaidFeesReportAction = (UnpaidFeesReportAction) Component
						.getInstance("unpaidFeesReportAction");
				unpaidFeesReportAction.runReport("UnpaidFeesReport",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
