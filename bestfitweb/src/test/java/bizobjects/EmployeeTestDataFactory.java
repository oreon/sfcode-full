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

			employee.setFirstName("John");
			employee.setLastName("Mark");
			employee.setDob(dateFormat.parse("2007.11.04 03:15:59 EST"));
			employee.setCode(8338);
			employee.getUserAccount().setUsername("zeta34472");
			employee.getUserAccount().setPassword("zeta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("gamma");
			employee.getPrimaryAddress().setCity("theta");
			employee.getPrimaryAddress().setZip("Mark");
			employee.getPrimaryAddress().setEmail("Wilson53016");
			employee.getPrimaryAddress().setCountry("John");
			employee.getPrimaryAddress().setState("Mark");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeTwo() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("delta");
			employee.setLastName("Mark");
			employee.setDob(dateFormat.parse("2007.10.08 11:19:19 EDT"));
			employee.setCode(320);
			employee.getUserAccount().setUsername("epsilon44576");
			employee.getUserAccount().setPassword("Mark");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Mark");
			employee.getPrimaryAddress().setCity("Wilson");
			employee.getPrimaryAddress().setZip("theta");
			employee.getPrimaryAddress().setEmail("Wilson94171");
			employee.getPrimaryAddress().setCountry("pi");
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

			employee.setFirstName("delta");
			employee.setLastName("zeta");
			employee.setDob(dateFormat.parse("2007.10.09 23:49:19 EDT"));
			employee.setCode(2739);
			employee.getUserAccount().setUsername("zeta27380");
			employee.getUserAccount().setPassword("John");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Malissa");
			employee.getPrimaryAddress().setCity("Wilson");
			employee.getPrimaryAddress().setZip("Wilson");
			employee.getPrimaryAddress().setEmail("Malissa18336");
			employee.getPrimaryAddress().setCountry("theta");
			employee.getPrimaryAddress().setState("Wilson");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFour() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("delta");
			employee.setLastName("delta");
			employee.setDob(dateFormat.parse("2007.10.15 15:41:30 EDT"));
			employee.setCode(8827);
			employee.getUserAccount().setUsername("Eric76424");
			employee.getUserAccount().setPassword("theta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("alpha");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("pi");
			employee.getPrimaryAddress().setEmail("pi56076");
			employee.getPrimaryAddress().setCountry("beta");
			employee.getPrimaryAddress().setState("Wilson");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("theta");
			employee.setLastName("John");
			employee.setDob(dateFormat.parse("2007.11.05 14:59:19 EST"));
			employee.setCode(1882);
			employee.getUserAccount().setUsername("alpha12507");
			employee.getUserAccount().setPassword("beta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("pi");
			employee.getPrimaryAddress().setCity("theta");
			employee.getPrimaryAddress().setZip("gamma");
			employee.getPrimaryAddress().setEmail("epsilon87899");
			employee.getPrimaryAddress().setCountry("pi");
			employee.getPrimaryAddress().setState("epsilon");

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
