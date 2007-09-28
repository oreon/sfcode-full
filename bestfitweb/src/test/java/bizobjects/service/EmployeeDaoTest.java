package bizobjects.service;

import bizobjects.Employee;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

public class EmployeeDaoTest extends AbstractJpaTests {

	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml"};
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {

	}

	public void testSave() {
		//test saving a new record and updating an existing record;
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {
		//return null;
	}

	public void testFindByLastName() {

		List<Employee> employees = employeeService.findByLastName("XXX");
		assertTrue(!employees.isEmpty());

		//negative test
		employees = employeeService.findByLastName("YYY");
		assertTrue(employees.isEmpty());

	}

	public void testFindByCode() {

		assertNotNull("Couldn't find a Employee with code XXX", employeeService
				.findByCode("XXX"));
		assertNull("Found a Employee with code YYY", employeeService
				.findByCode("YYY"));

	}

	public void testFindByUsername() {

		assertNotNull("Couldn't find a Employee with username XXX",
				employeeService.findByUsername("XXX"));
		assertNull("Found a Employee with username YYY", employeeService
				.findByUsername("YYY"));

	}

	public void testFindByEmail() {

		assertNotNull("Couldn't find a Employee with email XXX",
				employeeService.findByEmail("XXX"));
		assertNull("Found a Employee with email YYY", employeeService
				.findByEmail("YYY"));

	}

	public void testSearchByExample() {
		Employee employee = new Employee();
		//employee.setFirstName("Eri");
		List<Employee> employees = employeeService.searchByExample(employee);
		assertTrue(!employees.isEmpty());
	}

}
