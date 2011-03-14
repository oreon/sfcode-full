package com.oreon.smartsis.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class StudentsByGenderAndGradeTest extends BaseReportsTest {

	@Test
	public void testStudentsByGenderAndGradeReport() {
		try {
			runReportTest("StudentsByGenderAndGrade");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
