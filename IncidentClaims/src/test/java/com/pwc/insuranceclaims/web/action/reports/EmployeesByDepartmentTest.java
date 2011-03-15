package com.pwc.insuranceclaims.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class EmployeesByDepartmentTest extends BaseReportsTest {

	@Test
	public void testEmployeesByDepartmentReport() {
		try {
			runReportTest("EmployeesByDepartment");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
