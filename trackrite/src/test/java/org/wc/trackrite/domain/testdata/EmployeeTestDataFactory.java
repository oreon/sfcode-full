package org.wc.trackrite.domain.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class EmployeeTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.domain.Employee> {

	private List<org.wc.trackrite.domain.Employee> employees = new ArrayList<org.wc.trackrite.domain.Employee>();

	private static final Logger logger = Logger
			.getLogger(EmployeeTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.domain.EmployeeAction employeeAction;

	org.wc.trackrite.domain.testdata.DepartmentTestDataFactory departmentTestDataFactory = new org.wc.trackrite.domain.testdata.DepartmentTestDataFactory();

	// @In(create = true,  value="issueList")
	//com.nas.recovery.web.action.issues.IssueListQuery issueList;

	public void register(org.wc.trackrite.domain.Employee employee) {
		employees.add(employee);
	}

	public org.wc.trackrite.domain.Employee createEmployeeOne() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setFirstName("epsilon");

			employee.setLastName("pi");

			employee.getUser().setUserName("alpha");

			employee.getUser().setPassword("pi");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Lavendar");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.03 00:54:03 EST"));

			employee.setEmployeeNumber("gamma");

			employee.getHome().setPrimaryPhone("pi");

			employee.getHome().setSecondaryPhone("pi");

			employee.getHome().setCity("delta");

			employee.getHome().setStreetAddress("Wilson");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeTwo() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setFirstName("Wilson");

			employee.setLastName("alpha");

			employee.getUser().setUserName("delta");

			employee.getUser().setPassword("epsilon");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Lavendar");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.09 16:38:28 EST"));

			employee.setEmployeeNumber("alpha");

			employee.getHome().setPrimaryPhone("theta");

			employee.getHome().setSecondaryPhone("Mark");

			employee.getHome().setCity("zeta");

			employee.getHome().setStreetAddress("Eric");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeThree() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setFirstName("pi");

			employee.setLastName("Mark");

			employee.getUser().setUserName("Mark");

			employee.getUser().setPassword("Malissa");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("gamma");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.16 12:23:30 EST"));

			employee.setEmployeeNumber("epsilon");

			employee.getHome().setPrimaryPhone("alpha");

			employee.getHome().setSecondaryPhone("alpha");

			employee.getHome().setCity("gamma");

			employee.getHome().setStreetAddress("alpha");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeFour() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setFirstName("John");

			employee.setLastName("theta");

			employee.getUser().setUserName("pi");

			employee.getUser().setPassword("Mark");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Lavendar");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.02 10:52:21 EST"));

			employee.setEmployeeNumber("beta");

			employee.getHome().setPrimaryPhone("Lavendar");

			employee.getHome().setSecondaryPhone("Lavendar");

			employee.getHome().setCity("beta");

			employee.getHome().setStreetAddress("beta");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeFive() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setFirstName("alpha");

			employee.setLastName("beta");

			employee.getUser().setUserName("zeta");

			employee.getUser().setPassword("gamma");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("beta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.13 17:10:43 EST"));

			employee.setEmployeeNumber("Eric");

			employee.getHome().setPrimaryPhone("Wilson");

			employee.getHome().setSecondaryPhone("beta");

			employee.getHome().setCity("pi");

			employee.getHome().setStreetAddress("Eric");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public List<org.wc.trackrite.domain.Employee> createAll() {
		createEmployeeOne();
		createEmployeeTwo();
		createEmployeeThree();
		createEmployeeFour();
		createEmployeeFive();

		return employees;
	}

	@Override
	public List<org.wc.trackrite.domain.Employee> getListOfRecords() {
		return employees;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.domain.Employee e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.domain.Employee employee : employees) {
			persist(employee);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new EmployeeTestDataFactory().persistAll();
	}

}
