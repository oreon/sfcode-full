package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

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

			employee.setFirstName("Lavendar");
			employee.setLastName("epsilon");
			employee.setDob(dateFormat.parse("2007.09.15 23:56:15 EDT"));
			employee.setCode(1951);
			employee.getUserAccount().setUsername("alpha83514");
			employee.getUserAccount().setPassword("Lavendar");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("John");
			employee.getPrimaryAddress().setCity("theta");
			employee.getPrimaryAddress().setZip("Lavendar");
			employee.getPrimaryAddress().setEmail("epsilon74180");
			employee.getPrimaryAddress().setCountry("pi");
			employee.getPrimaryAddress().setState("alpha");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeTwo() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("theta");
			employee.setLastName("Mark");
			employee.setDob(dateFormat.parse("2007.10.10 03:09:03 EDT"));
			employee.setCode(4311);
			employee.getUserAccount().setUsername("alpha22334");
			employee.getUserAccount().setPassword("gamma");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("pi");
			employee.getPrimaryAddress().setCity("Mark");
			employee.getPrimaryAddress().setZip("Wilson");
			employee.getPrimaryAddress().setEmail("Eric19695");
			employee.getPrimaryAddress().setCountry("Eric");
			employee.getPrimaryAddress().setState("delta");

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
			employee.setLastName("Mark");
			employee.setDob(dateFormat.parse("2007.09.20 19:56:15 EDT"));
			employee.setCode(5246);
			employee.getUserAccount().setUsername("theta30415");
			employee.getUserAccount().setPassword("gamma");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("gamma");
			employee.getPrimaryAddress().setCity("Wilson");
			employee.getPrimaryAddress().setZip("pi");
			employee.getPrimaryAddress().setEmail("John64850");
			employee.getPrimaryAddress().setCountry("Malissa");
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

			employee.setFirstName("Wilson");
			employee.setLastName("gamma");
			employee.setDob(dateFormat.parse("2007.09.19 23:25:10 EDT"));
			employee.setCode(7917);
			employee.getUserAccount().setUsername("alpha20183");
			employee.getUserAccount().setPassword("Malissa");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("zeta");
			employee.getPrimaryAddress().setCity("epsilon");
			employee.getPrimaryAddress().setZip("John");
			employee.getPrimaryAddress().setEmail("Wilson51261");
			employee.getPrimaryAddress().setCountry("theta");
			employee.getPrimaryAddress().setState("beta");

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
			employee.setLastName("gamma");
			employee.setDob(dateFormat.parse("2007.09.20 11:32:23 EDT"));
			employee.setCode(4910);
			employee.getUserAccount().setUsername("delta26045");
			employee.getUserAccount().setPassword("Wilson");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("Malissa");
			employee.getPrimaryAddress().setZip("delta");
			employee.getPrimaryAddress().setEmail("Mark26242");
			employee.getPrimaryAddress().setCountry("pi");
			employee.getPrimaryAddress().setState("alpha");

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

}
