package bizobjects.service;

import bizobjects.Employee;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
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

			employeeInstance.setFirstName("alpha");
			employeeInstance.setLastName("beta");
			employeeInstance
					.setDob(dateFormat.parse("2007.11.07 20:27:52 EST"));
			employeeInstance.setCode(4636);
			employeeInstance.getUserAccount().setUsername("beta52255");
			employeeInstance.getUserAccount().setPassword("Eric");
			employeeInstance.getUserAccount().setEnabled(true);
			employeeInstance.getPrimaryAddress().setStreetAddress("beta");
			employeeInstance.getPrimaryAddress().setCity("theta");
			employeeInstance.getPrimaryAddress().setZip("delta");
			employeeInstance.getPrimaryAddress().setEmail("Mark69068");
			employeeInstance.getPrimaryAddress().setCountry("alpha");
			employeeInstance.getPrimaryAddress().setState("zeta");

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
				employee.setLastName("theta");
				employee.setDob(dateFormat.parse("2007.10.12 01:27:19 EDT"));
				employee.setCode(3010);
				employee.getUserAccount().setUsername("Malissa49116");
				employee.getUserAccount().setPassword("beta");
				employee.getUserAccount().setEnabled(true);
				employee.getPrimaryAddress().setStreetAddress("Lavendar");
				employee.getPrimaryAddress().setCity("alpha");
				employee.getPrimaryAddress().setZip("beta");
				employee.getPrimaryAddress().setEmail("gamma16233");
				employee.getPrimaryAddress().setCountry("beta");
				employee.getPrimaryAddress().setState("epsilon");

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

			employee.setFirstName("zeta");
			employee.setLastName("gamma");
			employee.setDob(dateFormat.parse("2007.10.07 21:47:52 EDT"));
			employee.setCode(1534);
			employee.getUserAccount().setUsername("zeta91541");
			employee.getUserAccount().setPassword("Lavendar");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("theta");
			employee.getPrimaryAddress().setCity("alpha");
			employee.getPrimaryAddress().setZip("Malissa");
			employee.getPrimaryAddress().setEmail("epsilon64747");
			employee.getPrimaryAddress().setCountry("Malissa");
			employee.getPrimaryAddress().setState("beta");

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
