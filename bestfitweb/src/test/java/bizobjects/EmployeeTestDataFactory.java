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

			employee.setFirstName("gamma");
			employee.setLastName("Wilson");
			employee.setDob(dateFormat.parse("2007.10.15 06:49:22 EDT"));
			employee.setCode(2742);
			employee.getUserAccount().setUsername("Wilson83588");
			employee.getUserAccount().setPassword("alpha");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Wilson");
			employee.getPrimaryAddress().setCity("epsilon");
			employee.getPrimaryAddress().setZip("Mark");
			employee.getPrimaryAddress().setEmail("zeta45833");

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
			employee.setLastName("alpha");
			employee.setDob(dateFormat.parse("2007.09.25 01:34:24 EDT"));
			employee.setCode(8957);
			employee.getUserAccount().setUsername("epsilon61060");
			employee.getUserAccount().setPassword("Eric");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Wilson");
			employee.getPrimaryAddress().setCity("Wilson");
			employee.getPrimaryAddress().setZip("zeta");
			employee.getPrimaryAddress().setEmail("alpha52161");

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
			employee.setLastName("Wilson");
			employee.setDob(dateFormat.parse("2007.10.05 10:48:16 EDT"));
			employee.setCode(9490);
			employee.getUserAccount().setUsername("pi91020");
			employee.getUserAccount().setPassword("Lavendar");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("zeta");
			employee.getPrimaryAddress().setCity("epsilon");
			employee.getPrimaryAddress().setZip("beta");
			employee.getPrimaryAddress().setEmail("Wilson87358");

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
			employee.setLastName("Wilson");
			employee.setDob(dateFormat.parse("2007.10.26 03:07:44 EDT"));
			employee.setCode(7163);
			employee.getUserAccount().setUsername("pi42785");
			employee.getUserAccount().setPassword("John");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("gamma");
			employee.getPrimaryAddress().setZip("Wilson");
			employee.getPrimaryAddress().setEmail("Malissa6795");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("Mark");
			employee.setLastName("zeta");
			employee.setDob(dateFormat.parse("2007.09.28 13:02:42 EDT"));
			employee.setCode(6203);
			employee.getUserAccount().setUsername("delta7713");
			employee.getUserAccount().setPassword("beta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Wilson");
			employee.getPrimaryAddress().setCity("gamma");
			employee.getPrimaryAddress().setZip("Mark");
			employee.getPrimaryAddress().setEmail("delta17358");

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
