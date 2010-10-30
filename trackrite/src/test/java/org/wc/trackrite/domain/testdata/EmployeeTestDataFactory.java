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

			employee.setFirstName("theta");

			employee.setLastName("epsilon");

			employee.getUser().setUserName("Mark");

			employee.getUser().setPassword("Lavendar");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Eric");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.22 03:31:55 EDT"));

			employee.setEmployeeNumber("zeta");

			employee.getHome().setPrimaryPhone("Eric");

			employee.getHome().setSecondaryPhone("theta");

			employee.getHome().setEmail("Wilson");

			employee.getMailing().setPrimaryPhone("Wilson");

			employee.getMailing().setSecondaryPhone("Malissa");

			employee.getMailing().setEmail("pi");

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

			employee.setFirstName("beta");

			employee.setLastName("zeta");

			employee.getUser().setUserName("theta");

			employee.getUser().setPassword("theta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Mark");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.21 17:16:24 EST"));

			employee.setEmployeeNumber("alpha");

			employee.getHome().setPrimaryPhone("epsilon");

			employee.getHome().setSecondaryPhone("Wilson");

			employee.getHome().setEmail("gamma");

			employee.getMailing().setPrimaryPhone("epsilon");

			employee.getMailing().setSecondaryPhone("Malissa");

			employee.getMailing().setEmail("delta");

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

			employee.setFirstName("Mark");

			employee.setLastName("Malissa");

			employee.getUser().setUserName("epsilon");

			employee.getUser().setPassword("gamma");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("delta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.16 14:56:24 EST"));

			employee.setEmployeeNumber("alpha");

			employee.getHome().setPrimaryPhone("epsilon");

			employee.getHome().setSecondaryPhone("Mark");

			employee.getHome().setEmail("delta");

			employee.getMailing().setPrimaryPhone("zeta");

			employee.getMailing().setSecondaryPhone("pi");

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

			employee.setFirstName("delta");

			employee.setLastName("Malissa");

			employee.getUser().setUserName("zeta");

			employee.getUser().setPassword("zeta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("theta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.10 02:00:50 EDT"));

			employee.setEmployeeNumber("Eric");

			employee.getHome().setPrimaryPhone("theta");

			employee.getHome().setSecondaryPhone("Eric");

			employee.getHome().setEmail("pi");

			employee.getMailing().setPrimaryPhone("beta");

			employee.getMailing().setSecondaryPhone("Wilson");

			employee.getMailing().setEmail("Malissa");

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

			employee.setFirstName("Eric");

			employee.setLastName("epsilon");

			employee.getUser().setUserName("Wilson");

			employee.getUser().setPassword("alpha");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("beta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.13 08:39:44 EST"));

			employee.setEmployeeNumber("John");

			employee.getHome().setPrimaryPhone("beta");

			employee.getHome().setSecondaryPhone("Lavendar");

			employee.getHome().setEmail("zeta");

			employee.getMailing().setPrimaryPhone("delta");

			employee.getMailing().setSecondaryPhone("delta");

			employee.getMailing().setEmail("epsilon");

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
