package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import bizobjects.service.EmployeeService;

public class EmployeeTestDataFactory extends AbstractTestDataFactory {

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

			employee.setFirstName("delta");
			employee.setLastName("gamma");
			employee.setDob(dateFormat.parse("2007.09.09 14:24:37 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("alpha68492");
			employee.getUserAccount().setPassword("delta");
			employee.getPrimaryAddress().setStreetAddress("alpha");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("delta");
			employee.getPrimaryAddress().setEmail("theta53165");

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
			employee.setLastName("pi");
			employee.setDob(dateFormat.parse("2007.09.21 15:12:59 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("pi1720");
			employee.getUserAccount().setPassword("gamma");
			employee.getPrimaryAddress().setStreetAddress("delta");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("pi");
			employee.getPrimaryAddress().setEmail("alpha45341");

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
			employee.setLastName("beta");
			employee.setDob(dateFormat.parse("2007.09.14 22:59:39 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("pi1692");
			employee.getUserAccount().setPassword("theta");
			employee.getPrimaryAddress().setStreetAddress("beta");
			employee.getPrimaryAddress().setCity("delta");
			employee.getPrimaryAddress().setZip("beta");
			employee.getPrimaryAddress().setEmail("beta29767");

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
			employee.setLastName("theta");
			employee.setDob(dateFormat.parse("2007.10.08 23:31:17 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("theta66253");
			employee.getUserAccount().setPassword("beta");
			employee.getPrimaryAddress().setStreetAddress("delta");
			employee.getPrimaryAddress().setCity("beta");
			employee.getPrimaryAddress().setZip("epsilon");
			employee.getPrimaryAddress().setEmail("alpha22441");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("pi");
			employee.setLastName("epsilon");
			employee.setDob(dateFormat.parse("2007.09.28 05:31:17 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("zeta73996");
			employee.getUserAccount().setPassword("beta");
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("delta");
			employee.getPrimaryAddress().setZip("theta");
			employee.getPrimaryAddress().setEmail("epsilon53140");

			register(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public Employee loadEmployee() {
		List<Employee> employees = employeeService.loadAll();

		if (employees.isEmpty()) {
			persistAll();
			employees = employeeService.loadAll();
		}

		return employees.get(new Random().nextInt(employees.size()));
	}

	List<Employee> getAllAsList() {

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
		if (!isPersistable())
			return;

		for (Employee employee : employees) {
			employeeService.save(employee);
		}
	}

	/** Will return a random number of PlacedOrders
	 * @return
	 */
	List<Employee> getFew() {
		List<Employee> all = getAllAsList();
		int numToChoose = new Random(1212343).nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<Employee>();
		List returnList = new ArrayList<Employee>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random(1212343).nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(numToChoose);
		}

		return returnList;
	}

}
