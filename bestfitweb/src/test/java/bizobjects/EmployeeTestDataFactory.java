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

			employee.setFirstName("gamma");
			employee.setLastName("theta");
			employee.setDob(dateFormat.parse("2007.11.21 21:58:41 EST"));
			employee.setAge(8799);
			employee.setCode(4516);
			employee.getUserAccount().setUsername("Eric88925");
			employee.getUserAccount().setPassword("gamma");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Eric");
			employee.getPrimaryAddress().setCity("alpha");
			employee.getPrimaryAddress().setZip("John");
			employee.getPrimaryAddress().setEmail("alpha83085");
			employee.getPrimaryAddress().setCountry("pi");
			employee.getPrimaryAddress().setState("delta");

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
			employee.setLastName("Eric");
			employee.setDob(dateFormat.parse("2007.11.07 09:47:36 EST"));
			employee.setAge(4955);
			employee.setCode(7601);
			employee.getUserAccount().setUsername("epsilon84009");
			employee.getUserAccount().setPassword("Wilson");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("zeta");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("theta");
			employee.getPrimaryAddress().setEmail("beta24975");
			employee.getPrimaryAddress().setCountry("Mark");
			employee.getPrimaryAddress().setState("zeta");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeThree() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("alpha");
			employee.setLastName("gamma");
			employee.setDob(dateFormat.parse("2007.11.18 16:57:03 EST"));
			employee.setAge(5189);
			employee.setCode(2539);
			employee.getUserAccount().setUsername("Malissa16761");
			employee.getUserAccount().setPassword("Mark");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("beta");
			employee.getPrimaryAddress().setCity("Malissa");
			employee.getPrimaryAddress().setZip("Lavendar");
			employee.getPrimaryAddress().setEmail("Mark8964");
			employee.getPrimaryAddress().setCountry("Mark");
			employee.getPrimaryAddress().setState("pi");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFour() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("alpha");
			employee.setLastName("Lavendar");
			employee.setDob(dateFormat.parse("2007.11.11 05:27:03 EST"));
			employee.setAge(5649);
			employee.setCode(2692);
			employee.getUserAccount().setUsername("epsilon47776");
			employee.getUserAccount().setPassword("zeta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("John");
			employee.getPrimaryAddress().setEmail("beta52301");
			employee.getPrimaryAddress().setCountry("alpha");
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

			employee.setFirstName("Eric");
			employee.setLastName("Wilson");
			employee.setDob(dateFormat.parse("2007.10.22 12:47:03 EDT"));
			employee.setAge(2403);
			employee.setCode(2366);
			employee.getUserAccount().setUsername("John4182");
			employee.getUserAccount().setPassword("theta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("alpha");
			employee.getPrimaryAddress().setCity("theta");
			employee.getPrimaryAddress().setZip("Eric");
			employee.getPrimaryAddress().setEmail("beta59456");
			employee.getPrimaryAddress().setCountry("delta");
			employee.getPrimaryAddress().setState("delta");

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
