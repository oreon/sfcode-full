package com.oreon.jshoppingcart.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.jshoppingcart.service.EmployeeService;

@Transactional
public class EmployeeTestDataFactory extends AbstractTestDataFactory<Employee> {

	List<Employee> employees = new ArrayList<Employee>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	EmployeeService employeeService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void register(Employee employee) {
		employees.add(employee);
	}

	public Employee createEmployeeOne() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("zeta");
			employee.setLastName("Wilson");
			employee.setEmpCode(4691);

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeTwo() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("gamma");
			employee.setLastName("Wilson");
			employee.setEmpCode(9425);

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeThree() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("delta");
			employee.setLastName("John");
			employee.setEmpCode(4708);

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFour() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("pi");
			employee.setLastName("alpha");
			employee.setEmpCode(8247);

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("alpha");
			employee.setLastName("delta");
			employee.setEmpCode(4335);

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee loadOneRecord() {
		List<Employee> employees = employeeService.loadAll();

		if (employees.isEmpty()) {
			persistAll();
			employees = employeeService.loadAll();
		}

		return employees.get(new Random().nextInt(employees.size()));
	}

	public List<Employee> getAllAsList() {

		if (employees.isEmpty()) {

			createEmployeeOne();
			createEmployeeTwo();
			createEmployeeThree();
			createEmployeeFour();
			createEmployeeFive();

		}

		return employees;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Employee employee : employees) {
			employeeService.save(employee);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Employee employee = createRandomEmployee();
			employeeService.save(employee);
		}
	}

	public Employee createRandomEmployee() {
		Employee employee = new Employee();

		employee.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		employee.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		employee.setEmpCode((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));

		return employee;
	}

}
