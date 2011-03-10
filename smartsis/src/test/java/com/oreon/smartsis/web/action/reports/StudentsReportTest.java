package com.oreon.smartsis.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class StudentsReportTest extends BaseReportsTest {

	@Test
	public void testStudentsReportReport() {
		try {
			runReportTest("StudentsReport");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
