package com.oreon.jshoppingcart.service;

import com.oreon.jshoppingcart.domain.Employee;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class EmployeeDaoTest extends AbstractJpaTests {

	protected Employee employeeInstance = new Employee();

	protected EmployeeService employeeService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	protected TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("employeeTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			employeeInstance.setFirstName("Eric");
			employeeInstance.setLastName("zeta");
			employeeInstance.setEmpCode(3964);

			employeeService.save(employeeInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			Employee employee = new Employee();

			try {

				employee.setFirstName("Mark");
				employee.setLastName("Wilson");
				employee.setEmpCode(3780);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			employeeService.save(employee);
			assertNotNull(employee.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Employee employee = (Employee) employeeTestDataFactory
					.loadOneRecord();

			employee.setFirstName("gamma");
			employee.setLastName("Malissa");
			employee.setEmpCode(7331);

			employeeService.save(employee);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(employeeService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Employee employee = employeeService.load(employeeInstance.getId());
			assertNotNull(employee.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Employee> employees = employeeService
					.searchByExample(employeeInstance);
			assertTrue(!employees.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
