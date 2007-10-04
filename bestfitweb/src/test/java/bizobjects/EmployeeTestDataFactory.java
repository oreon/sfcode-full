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

			employee.setFirstName("Eric");
			employee.setLastName("Lavendar");
			employee.setDob(dateFormat.parse("2007.10.25 06:58:06 EDT"));
			employee.setCode(7108);
			employee.getUserAccount().setUsername("alpha30955");
			employee.getUserAccount().setPassword("Eric");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("pi");
			employee.getPrimaryAddress().setCity("epsilon");
			employee.getPrimaryAddress().setZip("Eric");
			employee.getPrimaryAddress().setEmail("zeta37442");

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
			employee.setLastName("Lavendar");
			employee.setDob(dateFormat.parse("2007.10.11 10:05:18 EDT"));
			employee.setCode(895);
			employee.getUserAccount().setUsername("zeta25229");
			employee.getUserAccount().setPassword("Mark");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Malissa");
			employee.getPrimaryAddress().setCity("zeta");
			employee.getPrimaryAddress().setZip("beta");
			employee.getPrimaryAddress().setEmail("pi34498");

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
			employee.setLastName("Eric");
			employee.setDob(dateFormat.parse("2007.09.10 03:00:49 EDT"));
			employee.setCode(7913);
			employee.getUserAccount().setUsername("zeta68733");
			employee.getUserAccount().setPassword("Wilson");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("beta");
			employee.getPrimaryAddress().setCity("delta");
			employee.getPrimaryAddress().setZip("Eric");
			employee.getPrimaryAddress().setEmail("alpha27869");

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
			employee.setLastName("pi");
			employee.setDob(dateFormat.parse("2007.09.10 18:51:58 EDT"));
			employee.setCode(8243);
			employee.getUserAccount().setUsername("alpha7599");
			employee.getUserAccount().setPassword("John");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("Eric");
			employee.getPrimaryAddress().setZip("theta");
			employee.getPrimaryAddress().setEmail("alpha86361");

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
			employee.setLastName("gamma");
			employee.setDob(dateFormat.parse("2007.10.29 04:44:46 EDT"));
			employee.setCode(383);
			employee.getUserAccount().setUsername("Mark35026");
			employee.getUserAccount().setPassword("Lavendar");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("zeta");
			employee.getPrimaryAddress().setCity("Lavendar");
			employee.getPrimaryAddress().setZip("epsilon");
			employee.getPrimaryAddress().setEmail("theta19135");

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
