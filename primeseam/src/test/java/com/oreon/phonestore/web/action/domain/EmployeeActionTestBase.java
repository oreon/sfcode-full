package com.oreon.phonestore.web.action.domain;

import org.junit.Before;
import org.junit.Test;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Employee;

public class EmployeeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Employee> {

	EmployeeAction employeeAction = new EmployeeAction();

	@Before
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Employee> getAction() {
		return employeeAction;
	}

	@Test
	public void testRegister() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				EmployeeAction employeeAction = (EmployeeAction) org.jboss.seam.Component.getInstance("employeeAction");

				// assert(employeeAction.register()).equals("");
			}

		}.run();
	}

	@Test
	public void testLogin() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				EmployeeAction employeeAction = (EmployeeAction) org.jboss.seam.Component
						.getInstance("employeeAction");

				// assert(employeeAction.login()).equals("");
			}

		}.run();
	}

	@Test
	public void testRetrieveCredentials() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				EmployeeAction employeeAction = (EmployeeAction) org.jboss.seam.Component
						.getInstance("employeeAction");

				// assert(employeeAction.retrieveCredentials()).equals("");
			}

		}.run();
	}

}
