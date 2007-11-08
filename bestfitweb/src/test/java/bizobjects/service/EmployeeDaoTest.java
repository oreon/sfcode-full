package bizobjects.service;

import bizobjects.Employee;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

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

			employeeInstance.setFirstName("Malissa");
			employeeInstance.setLastName("John");
			employeeInstance
					.setDob(dateFormat.parse("2007.11.22 00:55:21 EST"));
			employeeInstance.setAge(7173);
			employeeInstance.setCode(5956);
			employeeInstance.getUserAccount().setUsername("Mark93563");
			employeeInstance.getUserAccount().setPassword("pi");
			employeeInstance.getUserAccount().setEnabled(true);
			employeeInstance.getPrimaryAddress().setStreetAddress("Eric");
			employeeInstance.getPrimaryAddress().setCity("beta");
			employeeInstance.getPrimaryAddress().setZip("Eric");
			employeeInstance.getPrimaryAddress().setEmail("John58751");
			employeeInstance.getPrimaryAddress().setCountry("beta");
			employeeInstance.getPrimaryAddress().setState("Mark");

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

				employee.setFirstName("John");
				employee.setLastName("epsilon");
				employee.setDob(dateFormat.parse("2007.11.21 15:41:29 EST"));
				employee.setAge(193);
				employee.setCode(8281);
				employee.getUserAccount().setUsername("alpha22327");
				employee.getUserAccount().setPassword("Mark");
				employee.getUserAccount().setEnabled(true);
				employee.getPrimaryAddress().setStreetAddress("Lavendar");
				employee.getPrimaryAddress().setCity("gamma");
				employee.getPrimaryAddress().setZip("delta");
				employee.getPrimaryAddress().setEmail("Wilson10151");
				employee.getPrimaryAddress().setCountry("John");
				employee.getPrimaryAddress().setState("delta");

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

			employee.setFirstName("gamma");
			employee.setLastName("theta");
			employee.setDob(dateFormat.parse("2007.11.24 14:17:36 EST"));
			employee.setAge(3293);
			employee.setCode(4656);
			employee.getUserAccount().setUsername("epsilon85484");
			employee.getUserAccount().setPassword("gamma");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("Mark");
			employee.getPrimaryAddress().setCity("zeta");
			employee.getPrimaryAddress().setZip("John");
			employee.getPrimaryAddress().setEmail("Eric26546");
			employee.getPrimaryAddress().setCountry("delta");
			employee.getPrimaryAddress().setState("theta");

			employeeService.save(employee);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(employeeService.getCount() > 0);
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
