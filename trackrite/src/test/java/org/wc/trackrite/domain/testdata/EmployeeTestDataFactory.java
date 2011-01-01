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

			employee.setLastName("Eric");

			employee.getUser().setUserName("Mark");

			employee.getUser().setPassword("zeta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("delta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.25 06:35:32 EST"));

			employee.setEmployeeNumber("epsilon");

			employee.getHome().setPrimaryPhone("pi");

			employee.getHome().setSecondaryPhone("pi");

			employee.getHome().setEmail("pi");

			employee.getMailing().setPrimaryPhone("epsilon");

			employee.getMailing().setSecondaryPhone("Mark");

			employee.getMailing().setEmail("gamma");

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

			employee.setFirstName("Mark");

			employee.setLastName("pi");

			employee.getUser().setUserName("Mark");

			employee.getUser().setPassword("epsilon");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Malissa");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.12.10 00:42:12 EST"));

			employee.setEmployeeNumber("Eric");

			employee.getHome().setPrimaryPhone("Mark");

			employee.getHome().setSecondaryPhone("beta");

			employee.getHome().setEmail("beta");

			employee.getMailing().setPrimaryPhone("beta");

			employee.getMailing().setSecondaryPhone("pi");

			employee.getMailing().setEmail("John");

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

			employee.setLastName("pi");

			employee.getUser().setUserName("alpha");

			employee.getUser().setPassword("John");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("John");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.12.24 13:46:05 EST"));

			employee.setEmployeeNumber("pi");

			employee.getHome().setPrimaryPhone("zeta");

			employee.getHome().setSecondaryPhone("beta");

			employee.getHome().setEmail("John");

			employee.getMailing().setPrimaryPhone("pi");

			employee.getMailing().setSecondaryPhone("theta");

			employee.getMailing().setEmail("Malissa");

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

			employee.setFirstName("theta");

			employee.setLastName("beta");

			employee.getUser().setUserName("John");

			employee.getUser().setPassword("Wilson");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("epsilon");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.12.05 10:21:07 EST"));

			employee.setEmployeeNumber("John");

			employee.getHome().setPrimaryPhone("zeta");

			employee.getHome().setSecondaryPhone("alpha");

			employee.getHome().setEmail("Wilson");

			employee.getMailing().setPrimaryPhone("theta");

			employee.getMailing().setSecondaryPhone("pi");

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

			employee.setFirstName("Mark");

			employee.setLastName("Malissa");

			employee.getUser().setUserName("gamma");

			employee.getUser().setPassword("Wilson");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("John");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.27 05:09:58 EST"));

			employee.setEmployeeNumber("Eric");

			employee.getHome().setPrimaryPhone("Lavendar");

			employee.getHome().setSecondaryPhone("Eric");

			employee.getHome().setEmail("pi");

			employee.getMailing().setPrimaryPhone("gamma");

			employee.getMailing().setSecondaryPhone("beta");

			employee.getMailing().setEmail("delta");

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
