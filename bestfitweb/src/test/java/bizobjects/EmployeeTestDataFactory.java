package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import org.witchcraft.model.support.TestDataFactory;

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

			employee.setFirstName("pi");
			employee.setLastName("theta");
			employee.setDob(dateFormat.parse("2007.10.17 22:43:06 EDT"));
			employee.setCode(3034);
			employee.getUserAccount().setUsername("Wilson7172");
			employee.getUserAccount().setPassword("beta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("alpha");
			employee.getPrimaryAddress().setCity("Lavendar");
			employee.getPrimaryAddress().setZip("beta");
			employee.getPrimaryAddress().setEmail("gamma98485");

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
			employee.setLastName("Lavendar");
			employee.setDob(dateFormat.parse("2007.09.29 04:33:39 EDT"));
			employee.setCode(6748);
			employee.getUserAccount().setUsername("gamma80724");
			employee.getUserAccount().setPassword("Eric");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("delta");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("Mark");
			employee.getPrimaryAddress().setEmail("alpha59073");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeThree() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("John");
			employee.setLastName("John");
			employee.setDob(dateFormat.parse("2007.10.18 21:32:01 EDT"));
			employee.setCode(3954);
			employee.getUserAccount().setUsername("Lavendar11491");
			employee.getUserAccount().setPassword("Mark");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Mark");
			employee.getPrimaryAddress().setCity("delta");
			employee.getPrimaryAddress().setZip("epsilon");
			employee.getPrimaryAddress().setEmail("gamma80609");

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
			employee.setLastName("Wilson");
			employee.setDob(dateFormat.parse("2007.09.13 15:37:31 EDT"));
			employee.setCode(6547);
			employee.getUserAccount().setUsername("John82686");
			employee.getUserAccount().setPassword("Wilson");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Lavendar");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("zeta");
			employee.getPrimaryAddress().setEmail("gamma31925");

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
			employee.setLastName("zeta");
			employee.setDob(dateFormat.parse("2007.10.22 15:06:26 EDT"));
			employee.setCode(9586);
			employee.getUserAccount().setUsername("beta50714");
			employee.getUserAccount().setPassword("John");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Wilson");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("epsilon");
			employee.getPrimaryAddress().setEmail("alpha50110");

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
