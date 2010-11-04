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

			employee.setFirstName("Lavendar");

			employee.setLastName("Wilson");

			employee.getUser().setUserName("John");

			employee.getUser().setPassword("gamma");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("beta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.20 04:14:41 EDT"));

			employee.setEmployeeNumber("John");

			employee.getHome().setPrimaryPhone("theta");

			employee.getHome().setSecondaryPhone("Wilson");

			employee.getHome().setEmail("epsilon");

			employee.getMailing().setPrimaryPhone("Malissa");

			employee.getMailing().setSecondaryPhone("alpha");

			employee.getMailing().setEmail("zeta");

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

			employee.getUser().setUserName("Malissa");

			employee.getUser().setPassword("theta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Wilson");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.11 08:43:32 EDT"));

			employee.setEmployeeNumber("epsilon");

			employee.getHome().setPrimaryPhone("delta");

			employee.getHome().setSecondaryPhone("pi");

			employee.getHome().setEmail("zeta");

			employee.getMailing().setPrimaryPhone("zeta");

			employee.getMailing().setSecondaryPhone("Wilson");

			employee.getMailing().setEmail("Wilson");

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

			employee.setFirstName("alpha");

			employee.setLastName("gamma");

			employee.getUser().setUserName("Wilson");

			employee.getUser().setPassword("zeta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("Malissa");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.15 13:44:41 EDT"));

			employee.setEmployeeNumber("epsilon");

			employee.getHome().setPrimaryPhone("gamma");

			employee.getHome().setSecondaryPhone("gamma");

			employee.getHome().setEmail("theta");

			employee.getMailing().setPrimaryPhone("theta");

			employee.getMailing().setSecondaryPhone("Lavendar");

			employee.getMailing().setEmail("gamma");

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

			employee.setFirstName("beta");

			employee.setLastName("Wilson");

			employee.getUser().setUserName("beta");

			employee.getUser().setPassword("theta");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("zeta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.11.27 15:31:54 EST"));

			employee.setEmployeeNumber("Malissa");

			employee.getHome().setPrimaryPhone("alpha");

			employee.getHome().setSecondaryPhone("John");

			employee.getHome().setEmail("Wilson");

			employee.getMailing().setPrimaryPhone("gamma");

			employee.getMailing().setSecondaryPhone("epsilon");

			employee.getMailing().setEmail("pi");

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

			employee.setLastName("Wilson");

			employee.getUser().setUserName("alpha");

			employee.getUser().setPassword("pi");

			employee.getUser().setEnabled(true);

			employee.getUser().setEmail("theta");

			employee.getUser().setLastLogin(
					dateFormat.parse("2010.10.24 18:13:32 EDT"));

			employee.setEmployeeNumber("zeta");

			employee.getHome().setPrimaryPhone("delta");

			employee.getHome().setSecondaryPhone("gamma");

			employee.getHome().setEmail("Malissa");

			employee.getMailing().setPrimaryPhone("zeta");

			employee.getMailing().setSecondaryPhone("John");

			employee.getMailing().setEmail("pi");

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
