package com.oreon.smartsis.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class EmployeeDepartmentCountTest extends BaseReportsTest {

	@Test
	public void testEmployeeDepartmentCountReport() {
		try {
			runReportTest("EmployeeDepartmentCount");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
