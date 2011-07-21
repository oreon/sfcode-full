package com.oreon.smartsis.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.smartsis.web.action.reports.StudentsByGenderAndGradeAction;

public class StudentsByGenderAndGradeTest extends BaseReportsTest {

	@Test
	public void testStudentsByGenderAndGradeReport() {
		try {
			runReportTest("StudentsByGenderAndGrade");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testStudentsByGenderAndGradeReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				StudentsByGenderAndGradeAction studentsByGenderAndGradeAction = (StudentsByGenderAndGradeAction) Component
						.getInstance("studentsByGenderAndGradeAction");
				studentsByGenderAndGradeAction.runReport(
						"StudentsByGenderAndGrade",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
