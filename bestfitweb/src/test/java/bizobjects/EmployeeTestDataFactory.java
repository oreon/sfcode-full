package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import bizobjects.service.EmployeeService;

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

			employee.setFirstName("theta");
			employee.setLastName("pi");
			employee.setDob(dateFormat.parse("2007.11.10 17:23:41 EST"));
			employee.setAge(4009);
			employee.setCode(4409);
			employee.getUserAccount().setUsername("Lavendar16592");
			employee.getUserAccount().setPassword("Lavendar");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("delta");
			employee.getPrimaryAddress().setCity("Lavendar");
			employee.getPrimaryAddress().setZip("Lavendar");
			employee.getPrimaryAddress().setEmail("zeta12999");
			employee.getPrimaryAddress().setCountry("gamma");
			employee.getPrimaryAddress().setState("Malissa");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeTwo() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("epsilon");
			employee.setLastName("beta");
			employee.setDob(dateFormat.parse("2007.11.28 08:56:29 EST"));
			employee.setAge(501);
			employee.setCode(3078);
			employee.getUserAccount().setUsername("Mark85350");
			employee.getUserAccount().setPassword("pi");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Eric");
			employee.getPrimaryAddress().setCity("Eric");
			employee.getPrimaryAddress().setZip("Wilson");
			employee.getPrimaryAddress().setEmail("epsilon12357");
			employee.getPrimaryAddress().setCountry("delta");
			employee.getPrimaryAddress().setState("theta");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeThree() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("Wilson");
			employee.setLastName("Lavendar");
			employee.setDob(dateFormat.parse("2007.10.30 01:02:03 EDT"));
			employee.setAge(2707);
			employee.setCode(7509);
			employee.getUserAccount().setUsername("Malissa2089");
			employee.getUserAccount().setPassword("zeta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Wilson");
			employee.getPrimaryAddress().setCity("theta");
			employee.getPrimaryAddress().setZip("theta");
			employee.getPrimaryAddress().setEmail("gamma72720");
			employee.getPrimaryAddress().setCountry("beta");
			employee.getPrimaryAddress().setState("gamma");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFour() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("beta");
			employee.setLastName("epsilon");
			employee.setDob(dateFormat.parse("2007.12.01 00:43:42 EST"));
			employee.setAge(660);
			employee.setCode(1970);
			employee.getUserAccount().setUsername("Malissa31923");
			employee.getUserAccount().setPassword("gamma");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Eric");
			employee.getPrimaryAddress().setCity("Wilson");
			employee.getPrimaryAddress().setZip("alpha");
			employee.getPrimaryAddress().setEmail("beta9797");
			employee.getPrimaryAddress().setCountry("zeta");
			employee.getPrimaryAddress().setState("epsilon");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("beta");
			employee.setLastName("Malissa");
			employee.setDob(dateFormat.parse("2007.11.09 20:53:42 EST"));
			employee.setAge(3879);
			employee.setCode(815);
			employee.getUserAccount().setUsername("gamma83686");
			employee.getUserAccount().setPassword("John");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("zeta");
			employee.getPrimaryAddress().setCity("beta");
			employee.getPrimaryAddress().setZip("Lavendar");
			employee.getPrimaryAddress().setEmail("alpha77328");
			employee.getPrimaryAddress().setCountry("Malissa");
			employee.getPrimaryAddress().setState("theta");

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
		employee.setDob((java.util.Date) RandomValueGeneratorFactory
				.createInstance("java.util.Date"));
		employee.setAge((Integer) RandomValueGeneratorFactory
				.createInstance("int"));
		employee.setCode((Integer) RandomValueGeneratorFactory
				.createInstance("int"));
		employee.getUserAccount().setUsername(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		employee.getUserAccount().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		employee.getUserAccount()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("boolean"));
		employee.getPrimaryAddress().setStreetAddress(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		employee.getPrimaryAddress().setCity(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		employee.getPrimaryAddress().setZip(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		employee.getPrimaryAddress().setEmail(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		employee.getPrimaryAddress().setCountry(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		employee.getPrimaryAddress().setState(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		return employee;
	}

}
