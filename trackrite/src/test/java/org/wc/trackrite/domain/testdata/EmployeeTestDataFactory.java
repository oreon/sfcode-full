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

			employee.setFirstName("delta");

			employee.setLastName("Mark");

			employee.getUser().setUserName("Wilson");

			employee.getUser().setPassword("epsilon");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("delta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.11 18:32:34 EST"));

			employee.setEmployeeNumber("gamma");

			employee.getHome().setPrimaryPhone("Wilson");

			employee.getHome().setSecondaryPhone("zeta");

			employee.getHome().setEmail("zeta");

			employee.getMailing().setPrimaryPhone("John");

			employee.getMailing().setSecondaryPhone("theta");

			employee.getMailing().setEmail("beta");

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

			employee.setFirstName("Malissa");

			employee.setLastName("theta");

			employee.getUser().setUserName("alpha");

			employee.getUser().setPassword("Mark");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("epsilon");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.18 11:33:39 EDT"));

			employee.setEmployeeNumber("delta");

			employee.getHome().setPrimaryPhone("beta");

			employee.getHome().setSecondaryPhone("epsilon");

			employee.getHome().setEmail("alpha");

			employee.getMailing().setPrimaryPhone("theta");

			employee.getMailing().setSecondaryPhone("Eric");

			employee.getMailing().setEmail("Malissa");

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

			employee.setFirstName("Wilson");

			employee.setLastName("John");

			employee.getUser().setUserName("alpha");

			employee.getUser().setPassword("Wilson");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("pi");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.08 22:56:27 EST"));

			employee.setEmployeeNumber("delta");

			employee.getHome().setPrimaryPhone("Mark");

			employee.getHome().setSecondaryPhone("epsilon");

			employee.getHome().setEmail("gamma");

			employee.getMailing().setPrimaryPhone("pi");

			employee.getMailing().setSecondaryPhone("epsilon");

			employee.getMailing().setEmail("pi");

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

			employee.setLastName("alpha");

			employee.getUser().setUserName("Lavendar");

			employee.getUser().setPassword("pi");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Malissa");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.24 16:14:12 EDT"));

			employee.setEmployeeNumber("alpha");

			employee.getHome().setPrimaryPhone("epsilon");

			employee.getHome().setSecondaryPhone("gamma");

			employee.getHome().setEmail("alpha");

			employee.getMailing().setPrimaryPhone("alpha");

			employee.getMailing().setSecondaryPhone("zeta");

			employee.getMailing().setEmail("alpha");

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

			employee.setLastName("gamma");

			employee.getUser().setUserName("zeta");

			employee.getUser().setPassword("Mark");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("beta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.09.30 23:11:58 EDT"));

			employee.setEmployeeNumber("Mark");

			employee.getHome().setPrimaryPhone("John");

			employee.getHome().setSecondaryPhone("zeta");

			employee.getHome().setEmail("epsilon");

			employee.getMailing().setPrimaryPhone("epsilon");

			employee.getMailing().setSecondaryPhone("epsilon");

			employee.getMailing().setEmail("pi");

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
