package bizobjects.service;

import bizobjects.Employee;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;

public class EmployeeDaoTest extends AbstractJpaTests {

	protected Employee employeeInstance = new Employee();

	protected EmployeeService employeeService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	protected TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("employeeTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			employeeInstance.setFirstName("Wilson");
			employeeInstance.setLastName("zeta");
			employeeInstance
					.setDob(dateFormat.parse("2007.09.11 00:14:11 EDT"));
			employeeInstance.setCode(6820);
			employeeInstance.getUserAccount().setUsername("pi94254");
			employeeInstance.getUserAccount().setPassword("John");
			employeeInstance.getUserAccount().setEnabled(true);
			employeeInstance.getPrimaryAddress().setStreetAddress("gamma");
			employeeInstance.getPrimaryAddress().setCity("gamma");
			employeeInstance.getPrimaryAddress().setZip("beta");
			employeeInstance.getPrimaryAddress().setEmail("alpha93064");

			employeeService.save(employeeInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			Employee employee = new Employee();

			try {

				employee.setFirstName("Mark");
				employee.setLastName("zeta");
				employee.setDob(dateFormat.parse("2007.10.15 19:18:41 EDT"));
				employee.setCode(423);
				employee.getUserAccount().setUsername("Eric79751");
				employee.getUserAccount().setPassword("alpha");
				employee.getUserAccount().setEnabled(true);
				employee.getPrimaryAddress().setStreetAddress("delta");
				employee.getPrimaryAddress().setCity("beta");
				employee.getPrimaryAddress().setZip("epsilon");
				employee.getPrimaryAddress().setEmail("Malissa81071");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			employeeService.save(employee);
			assertNotNull(employee.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Employee employee = (Employee) employeeTestDataFactory
					.loadOneRecord();

			employee.setFirstName("epsilon");
			employee.setLastName("epsilon");
			employee.setDob(dateFormat.parse("2007.10.09 21:13:39 EDT"));
			employee.setCode(2513);
			employee.getUserAccount().setUsername("alpha90361");
			employee.getUserAccount().setPassword("beta");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("Lavendar");
			employee.getPrimaryAddress().setZip("gamma");
			employee.getPrimaryAddress().setEmail("pi86697");

			employeeService.save(employee);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Employee employee = employeeService.load(employeeInstance.getId());
			assertNotNull(employee.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByLastName() {
		if (!bTest)
			return;

		List<Employee> employees = employeeService
				.findByLastName(employeeInstance.getLastName());
		assertTrue(!employees.isEmpty());

		//negative test
		//employees = 
		employeeService.findByLastName(employeeInstance.getLastName());
		//assertTrue(employees.isEmpty()); 

	}

	public void testFindByCode() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Employee with code ", employeeService
				.findByCode(employeeInstance.getCode()));
		//assertNull("Found a Employee with code YYY", employeeService.findByCode("YYY"));			

	}

	public void testFindByUsername() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Employee with username ",
				employeeService.findByUsername(employeeInstance
						.getUserAccount().getUsername()));
		//assertNull("Found a Employee with username YYY", employeeService.findByUsername("YYY"));			

	}

	public void testFindByEmail() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Employee with email ", employeeService
				.findByEmail(employeeInstance.getPrimaryAddress().getEmail()));
		//assertNull("Found a Employee with email YYY", employeeService.findByEmail("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<Employee> employees = employeeService
					.searchByExample(employeeInstance);
			assertTrue(!employees.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
