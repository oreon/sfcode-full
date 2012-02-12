package com.hrb.tservices.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.hrb.tservices.web.action.reports.TaxNewsByPartnerReportAction;

public class TaxNewsByPartnerReportTest extends BaseReportsTest {

	@Test
	public void testTaxNewsByPartnerReportReport() {
		try {
			runReportTest("TaxNewsByPartnerReport");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testTaxNewsByPartnerReportReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				TaxNewsByPartnerReportAction taxNewsByPartnerReportAction = (TaxNewsByPartnerReportAction) Component
						.getInstance("taxNewsByPartnerReportAction");
				taxNewsByPartnerReportAction.runReport(
						"TaxNewsByPartnerReport",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
