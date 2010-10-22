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

			employee.setFirstName("beta");

			employee.setLastName("zeta");

			employee.getUser().setUserName("delta");

			employee.getUser().setPassword("delta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("theta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.01 12:35:39 EDT"));

			employee.setEmployeeNumber("John");

			employee.getHome().setPrimaryPhone("Wilson");

			employee.getHome().setSecondaryPhone("Malissa");

			employee.getHome().setEmail("gamma");

			employee.getMailing().setPrimaryPhone("gamma");

			employee.getMailing().setSecondaryPhone("John");

			employee.getMailing().setEmail("delta");

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

			employee.setFirstName("Lavendar");

			employee.setLastName("delta");

			employee.getUser().setUserName("delta");

			employee.getUser().setPassword("Eric");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("beta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.02 11:13:25 EDT"));

			employee.setEmployeeNumber("gamma");

			employee.getHome().setPrimaryPhone("Mark");

			employee.getHome().setSecondaryPhone("John");

			employee.getHome().setEmail("theta");

			employee.getMailing().setPrimaryPhone("delta");

			employee.getMailing().setSecondaryPhone("epsilon");

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

			employee.getUser().setUserName("gamma");

			employee.getUser().setPassword("theta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Lavendar");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.07 03:41:10 EDT"));

			employee.setEmployeeNumber("theta");

			employee.getHome().setPrimaryPhone("Wilson");

			employee.getHome().setSecondaryPhone("Eric");

			employee.getHome().setEmail("Eric");

			employee.getMailing().setPrimaryPhone("pi");

			employee.getMailing().setSecondaryPhone("Lavendar");

			employee.getMailing().setEmail("John");

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

			employee.setFirstName("alpha");

			employee.setLastName("John");

			employee.getUser().setUserName("Mark");

			employee.getUser().setPassword("Eric");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("John");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.04 07:32:52 EDT"));

			employee.setEmployeeNumber("pi");

			employee.getHome().setPrimaryPhone("Eric");

			employee.getHome().setSecondaryPhone("Eric");

			employee.getHome().setEmail("Eric");

			employee.getMailing().setPrimaryPhone("Eric");

			employee.getMailing().setSecondaryPhone("Mark");

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

			employee.setFirstName("beta");

			employee.setLastName("delta");

			employee.getUser().setUserName("pi");

			employee.getUser().setPassword("beta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("pi");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.04 19:07:18 EDT"));

			employee.setEmployeeNumber("epsilon");

			employee.getHome().setPrimaryPhone("John");

			employee.getHome().setSecondaryPhone("epsilon");

			employee.getHome().setEmail("alpha");

			employee.getMailing().setPrimaryPhone("Mark");

			employee.getMailing().setSecondaryPhone("delta");

			employee.getMailing().setEmail("Lavendar");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee getRandomRecord() {

		if (employees.isEmpty()) {
			createAll();
		}

		return employees.get(new Random().nextInt(employees.size()));
	}

	public List<org.wc.trackrite.domain.Employee> createAll() {
		createEmployeeOne();
		createEmployeeTwo();
		createEmployeeThree();
		createEmployeeFour();
		createEmployeeFive();

		return employees;
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
