package com.oreon.smartsis.web.action.reports;

import org.jboss.seam.Component;
import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

public class EmployeeDepartmentCountTest extends BaseReportsTest {

	@Test
	public void testEmployeeDepartmentCountReport() {
		try {
			runReportTest("EmployeeDepartmentCount");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testEmployeeDepartmentCountReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				EmployeeDepartmentCountAction employeeDepartmentCountAction = (EmployeeDepartmentCountAction) Component
						.getInstance("employeeDepartmentCountAction");
				employeeDepartmentCountAction.runReport(
						"EmployeeDepartmentCount",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
