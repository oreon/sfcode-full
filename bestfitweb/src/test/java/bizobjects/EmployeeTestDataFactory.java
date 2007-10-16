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

			employee.setFirstName("Eric");
			employee.setLastName("delta");
			employee.setDob(dateFormat.parse("2007.10.27 03:51:12 EDT"));
			employee.setCode(2548);
			employee.getUserAccount().setUsername("pi99910");
			employee.getUserAccount().setPassword("Mark");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("zeta");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("zeta");
			employee.getPrimaryAddress().setEmail("zeta51790");
			employee.getPrimaryAddress().setCountry("Eric");
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

			employee.setFirstName("Lavendar");
			employee.setLastName("pi");
			employee.setDob(dateFormat.parse("2007.10.17 07:30:40 EDT"));
			employee.setCode(8580);
			employee.getUserAccount().setUsername("gamma32785");
			employee.getUserAccount().setPassword("pi");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("gamma");
			employee.getPrimaryAddress().setCity("Mark");
			employee.getPrimaryAddress().setZip("Wilson");
			employee.getPrimaryAddress().setEmail("Wilson32212");
			employee.getPrimaryAddress().setCountry("Wilson");
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

			employee.setFirstName("Lavendar");
			employee.setLastName("pi");
			employee.setDob(dateFormat.parse("2007.10.25 13:16:14 EDT"));
			employee.setCode(4748);
			employee.getUserAccount().setUsername("epsilon70020");
			employee.getUserAccount().setPassword("Eric");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("Eric");
			employee.getPrimaryAddress().setZip("alpha");
			employee.getPrimaryAddress().setEmail("zeta99778");
			employee.getPrimaryAddress().setCountry("alpha");
			employee.getPrimaryAddress().setState("epsilon");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFour() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("Mark");
			employee.setLastName("zeta");
			employee.setDob(dateFormat.parse("2007.09.29 04:49:34 EDT"));
			employee.setCode(7151);
			employee.getUserAccount().setUsername("zeta53284");
			employee.getUserAccount().setPassword("pi");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("gamma");
			employee.getPrimaryAddress().setCity("John");
			employee.getPrimaryAddress().setZip("pi");
			employee.getPrimaryAddress().setEmail("beta60681");
			employee.getPrimaryAddress().setCountry("Wilson");
			employee.getPrimaryAddress().setState("Lavendar");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("epsilon");
			employee.setLastName("Lavendar");
			employee.setDob(dateFormat.parse("2007.10.01 22:06:14 EDT"));
			employee.setCode(2203);
			employee.getUserAccount().setUsername("Mark35089");
			employee.getUserAccount().setPassword("epsilon");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Mark");
			employee.getPrimaryAddress().setCity("beta");
			employee.getPrimaryAddress().setZip("pi");
			employee.getPrimaryAddress().setEmail("epsilon32586");
			employee.getPrimaryAddress().setCountry("epsilon");
			employee.getPrimaryAddress().setState("Malissa");

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
