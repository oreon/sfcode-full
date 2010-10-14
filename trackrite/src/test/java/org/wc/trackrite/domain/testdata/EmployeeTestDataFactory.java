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

			employee.setEmployeeNumber("Lavendar");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			//	 employee.addAllIssues(issuesTestDataFactory.getFewRandomRecords());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeTwo() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setEmployeeNumber("epsilon");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			//	 employee.addAllIssues(issuesTestDataFactory.getFewRandomRecords());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeThree() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setEmployeeNumber("delta");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			//	 employee.addAllIssues(issuesTestDataFactory.getFewRandomRecords());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeFour() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setEmployeeNumber("Wilson");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			//	 employee.addAllIssues(issuesTestDataFactory.getFewRandomRecords());

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public org.wc.trackrite.domain.Employee createEmployeeFive() {
		org.wc.trackrite.domain.Employee employee = new org.wc.trackrite.domain.Employee();

		try {

			employee.setEmployeeNumber("gamma");

			employee.setDepartment(departmentTestDataFactory.getRandomRecord());

			//	 employee.addAllIssues(issuesTestDataFactory.getFewRandomRecords());

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
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (employeeAction == null)
			employeeAction = (com.nas.recovery.web.action.domain.EmployeeAction) Component
					.getInstance("employeeAction");

		for (org.wc.trackrite.domain.Employee employee : employees) {
			//try {
			employeeAction.setInstance(employee);
			employeeAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" Employee " + employee.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new EmployeeTestDataFactory().persistAll();
	}

}
