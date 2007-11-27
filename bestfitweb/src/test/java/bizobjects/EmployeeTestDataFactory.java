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

			employee.setFirstName("beta");
			employee.setLastName("alpha");
			employee.setDob(dateFormat.parse("2007.11.24 15:14:58 EST"));
			employee.setAge(8397);
			employee.setCode(160);
			employee.getUserAccount().setUsername("beta95316");
			employee.getUserAccount().setPassword("zeta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("zeta");
			employee.getPrimaryAddress().setZip("Mark");
			employee.getPrimaryAddress().setEmail("beta53698");
			employee.getPrimaryAddress().setCountry("Wilson");
			employee.getPrimaryAddress().setState("Wilson");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeTwo() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("Wilson");
			employee.setLastName("delta");
			employee.setDob(dateFormat.parse("2007.12.02 08:10:33 EST"));
			employee.setAge(4927);
			employee.setCode(6650);
			employee.getUserAccount().setUsername("alpha77433");
			employee.getUserAccount().setPassword("Lavendar");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("pi");
			employee.getPrimaryAddress().setCity("Lavendar");
			employee.getPrimaryAddress().setZip("zeta");
			employee.getPrimaryAddress().setEmail("zeta73441");
			employee.getPrimaryAddress().setCountry("pi");
			employee.getPrimaryAddress().setState("beta");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeThree() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("Malissa");
			employee.setLastName("Wilson");
			employee.setDob(dateFormat.parse("2007.11.21 04:58:18 EST"));
			employee.setAge(4577);
			employee.setCode(4542);
			employee.getUserAccount().setUsername("Mark37221");
			employee.getUserAccount().setPassword("Mark");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("theta");
			employee.getPrimaryAddress().setCity("Eric");
			employee.getPrimaryAddress().setZip("gamma");
			employee.getPrimaryAddress().setEmail("epsilon88607");
			employee.getPrimaryAddress().setCountry("Malissa");
			employee.getPrimaryAddress().setState("theta");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFour() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("gamma");
			employee.setLastName("zeta");
			employee.setDob(dateFormat.parse("2007.10.29 14:08:18 EDT"));
			employee.setAge(7066);
			employee.setCode(2657);
			employee.getUserAccount().setUsername("Wilson7892");
			employee.getUserAccount().setPassword("Wilson");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Eric");
			employee.getPrimaryAddress().setCity("theta");
			employee.getPrimaryAddress().setZip("alpha");
			employee.getPrimaryAddress().setEmail("Mark83995");
			employee.getPrimaryAddress().setCountry("Wilson");
			employee.getPrimaryAddress().setState("delta");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("delta");
			employee.setLastName("Wilson");
			employee.setDob(dateFormat.parse("2007.11.23 08:17:46 EST"));
			employee.setAge(5752);
			employee.setCode(5727);
			employee.getUserAccount().setUsername("Malissa36087");
			employee.getUserAccount().setPassword("epsilon");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Mark");
			employee.getPrimaryAddress().setCity("Wilson");
			employee.getPrimaryAddress().setZip("gamma");
			employee.getPrimaryAddress().setEmail("Eric98777");
			employee.getPrimaryAddress().setCountry("delta");
			employee.getPrimaryAddress().setState("John");

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
