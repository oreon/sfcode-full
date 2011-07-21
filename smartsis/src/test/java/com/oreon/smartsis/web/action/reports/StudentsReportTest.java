package com.oreon.smartsis.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.smartsis.web.action.reports.StudentsReportAction;

public class StudentsReportTest extends BaseReportsTest {

	@Test
	public void testStudentsReportReport() {
		try {
			runReportTest("StudentsReport");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testStudentsReportReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				StudentsReportAction studentsReportAction = (StudentsReportAction) Component
						.getInstance("studentsReportAction");
				studentsReportAction.runReport("StudentsReport",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
