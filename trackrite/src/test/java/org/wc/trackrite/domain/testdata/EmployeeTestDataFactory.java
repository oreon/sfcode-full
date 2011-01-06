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

			employee.setFirstName("zeta");

			employee.setLastName("Wilson");

			employee.getUser().setUserName("alpha");

			employee.getUser().setPassword("gamma");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("delta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.12.15 08:48:59 EST"));

			employee.setEmployeeNumber("epsilon");

			employee.getHome().setPrimaryPhone("zeta");

			employee.getHome().setSecondaryPhone("Malissa");

			employee.getHome().setCity("theta");

			employee.getHome().setStreetAddress("theta");

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

			employee.setFirstName("epsilon");

			employee.setLastName("epsilon");

			employee.getUser().setUserName("Lavendar");

			employee.getUser().setPassword("epsilon");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("gamma");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.28 20:02:19 EST"));

			employee.setEmployeeNumber("Malissa");

			employee.getHome().setPrimaryPhone("beta");

			employee.getHome().setSecondaryPhone("John");

			employee.getHome().setCity("zeta");

			employee.getHome().setStreetAddress("Wilson");

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

			employee.setFirstName("beta");

			employee.setLastName("Lavendar");

			employee.getUser().setUserName("Wilson");

			employee.getUser().setPassword("Malissa");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("alpha");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.27 17:40:05 EST"));

			employee.setEmployeeNumber("epsilon");

			employee.getHome().setPrimaryPhone("Eric");

			employee.getHome().setSecondaryPhone("alpha");

			employee.getHome().setCity("Mark");

			employee.getHome().setStreetAddress("beta");

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

			employee.setFirstName("Eric");

			employee.setLastName("Eric");

			employee.getUser().setUserName("gamma");

			employee.getUser().setPassword("delta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("theta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.19 21:49:32 EST"));

			employee.setEmployeeNumber("Eric");

			employee.getHome().setPrimaryPhone("gamma");

			employee.getHome().setSecondaryPhone("Mark");

			employee.getHome().setCity("Lavendar");

			employee.getHome().setStreetAddress("zeta");

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

			employee.setFirstName("John");

			employee.setLastName("gamma");

			employee.getUser().setUserName("epsilon");

			employee.getUser().setPassword("alpha");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Malissa");

			employee.getUser().setLastLogin(
					dateFormat.parse("2011.01.29 14:07:17 EST"));

			employee.setEmployeeNumber("alpha");

			employee.getHome().setPrimaryPhone("beta");

			employee.getHome().setSecondaryPhone("gamma");

			employee.getHome().setCity("Lavendar");

			employee.getHome().setStreetAddress("zeta");

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
