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

			employee.setFirstName("alpha");

			employee.setLastName("alpha");

			employee.getUser().setUserName("pi");

			employee.getUser().setPassword("pi");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("John");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.20 17:37:39 EDT"));

			employee.setEmployeeNumber("alpha");

			employee.getHome().setPrimaryPhone("Eric");

			employee.getHome().setSecondaryPhone("delta");

			employee.getHome().setEmail("Mark");

			employee.getMailing().setPrimaryPhone("alpha");

			employee.getMailing().setSecondaryPhone("pi");

			employee.getMailing().setEmail("alpha");

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

			employee.setFirstName("Eric");

			employee.setLastName("Wilson");

			employee.getUser().setUserName("Wilson");

			employee.getUser().setPassword("Wilson");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("epsilon");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.12 06:37:06 EST"));

			employee.setEmployeeNumber("theta");

			employee.getHome().setPrimaryPhone("epsilon");

			employee.getHome().setSecondaryPhone("Eric");

			employee.getHome().setEmail("Lavendar");

			employee.getMailing().setPrimaryPhone("alpha");

			employee.getMailing().setSecondaryPhone("alpha");

			employee.getMailing().setEmail("Mark");

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

			employee.setFirstName("John");

			employee.setLastName("Malissa");

			employee.getUser().setUserName("epsilon");

			employee.getUser().setPassword("John");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Mark");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.18 13:33:46 EDT"));

			employee.setEmployeeNumber("gamma");

			employee.getHome().setPrimaryPhone("theta");

			employee.getHome().setSecondaryPhone("alpha");

			employee.getHome().setEmail("gamma");

			employee.getMailing().setPrimaryPhone("alpha");

			employee.getMailing().setSecondaryPhone("alpha");

			employee.getMailing().setEmail("theta");

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

			employee.setFirstName("pi");

			employee.setLastName("theta");

			employee.getUser().setUserName("John");

			employee.getUser().setPassword("theta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("gamma");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.12.01 16:50:26 EST"));

			employee.setEmployeeNumber("alpha");

			employee.getHome().setPrimaryPhone("Malissa");

			employee.getHome().setSecondaryPhone("John");

			employee.getHome().setEmail("John");

			employee.getMailing().setPrimaryPhone("pi");

			employee.getMailing().setSecondaryPhone("beta");

			employee.getMailing().setEmail("John");

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

			employee.setFirstName("zeta");

			employee.setLastName("Mark");

			employee.getUser().setUserName("theta");

			employee.getUser().setPassword("theta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Eric");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.17 12:44:51 EST"));

			employee.setEmployeeNumber("John");

			employee.getHome().setPrimaryPhone("gamma");

			employee.getHome().setSecondaryPhone("John");

			employee.getHome().setEmail("Mark");

			employee.getMailing().setPrimaryPhone("pi");

			employee.getMailing().setSecondaryPhone("Mark");

			employee.getMailing().setEmail("zeta");

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
