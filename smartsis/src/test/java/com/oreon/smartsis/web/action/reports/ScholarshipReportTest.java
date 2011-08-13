package com.oreon.smartsis.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.smartsis.web.action.reports.ScholarshipReportAction;

public class ScholarshipReportTest extends BaseReportsTest {

	@Test
	public void testScholarshipReportReport() {
		try {
			runReportTest("ScholarshipReport");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testScholarshipReportReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				ScholarshipReportAction scholarshipReportAction = (ScholarshipReportAction) Component
						.getInstance("scholarshipReportAction");
				scholarshipReportAction.runReport("ScholarshipReport",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
