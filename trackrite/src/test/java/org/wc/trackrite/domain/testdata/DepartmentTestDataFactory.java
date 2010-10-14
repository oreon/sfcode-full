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

public class DepartmentTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.domain.Department> {

	private List<org.wc.trackrite.domain.Department> departments = new ArrayList<org.wc.trackrite.domain.Department>();

	private static final Logger logger = Logger
			.getLogger(DepartmentTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.domain.DepartmentAction departmentAction;

	// @In(create = true,  value="employeeList")
	//com.nas.recovery.web.action.domain.EmployeeListQuery employeeList;

	public void register(org.wc.trackrite.domain.Department department) {
		departments.add(department);
	}

	public org.wc.trackrite.domain.Department createDepartmentOne() {
		org.wc.trackrite.domain.Department department = new org.wc.trackrite.domain.Department();

		try {

			department.setName("delta");

			//	 department.addAllEmployees(employeesTestDataFactory.getFewRandomRecords());

			register(department);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return department;
	}

	public org.wc.trackrite.domain.Department createDepartmentTwo() {
		org.wc.trackrite.domain.Department department = new org.wc.trackrite.domain.Department();

		try {

			department.setName("pi");

			//	 department.addAllEmployees(employeesTestDataFactory.getFewRandomRecords());

			register(department);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return department;
	}

	public org.wc.trackrite.domain.Department createDepartmentThree() {
		org.wc.trackrite.domain.Department department = new org.wc.trackrite.domain.Department();

		try {

			department.setName("delta");

			//	 department.addAllEmployees(employeesTestDataFactory.getFewRandomRecords());

			register(department);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return department;
	}

	public org.wc.trackrite.domain.Department createDepartmentFour() {
		org.wc.trackrite.domain.Department department = new org.wc.trackrite.domain.Department();

		try {

			department.setName("beta");

			//	 department.addAllEmployees(employeesTestDataFactory.getFewRandomRecords());

			register(department);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return department;
	}

	public org.wc.trackrite.domain.Department createDepartmentFive() {
		org.wc.trackrite.domain.Department department = new org.wc.trackrite.domain.Department();

		try {

			department.setName("Lavendar");

			//	 department.addAllEmployees(employeesTestDataFactory.getFewRandomRecords());

			register(department);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return department;
	}

	public org.wc.trackrite.domain.Department getRandomRecord() {

		if (departments.isEmpty()) {
			createAll();
		}

		return departments.get(new Random().nextInt(departments.size()));
	}

	public List<org.wc.trackrite.domain.Department> createAll() {
		createDepartmentOne();
		createDepartmentTwo();
		createDepartmentThree();
		createDepartmentFour();
		createDepartmentFive();

		return departments;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (departmentAction == null)
			departmentAction = (com.nas.recovery.web.action.domain.DepartmentAction) Component
					.getInstance("departmentAction");

		for (org.wc.trackrite.domain.Department department : departments) {
			//try {
			departmentAction.setInstance(department);
			departmentAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" Department " + department.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new DepartmentTestDataFactory().persistAll();
	}

}
