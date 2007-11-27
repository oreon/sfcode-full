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

			employeeInstance.setFirstName("epsilon");
			employeeInstance.setLastName("Lavendar");
			employeeInstance
					.setDob(dateFormat.parse("2007.11.20 02:22:11 EST"));
			employeeInstance.setAge(6706);
			employeeInstance.setCode(4070);
			employeeInstance.getUserAccount().setUsername("zeta37981");
			employeeInstance.getUserAccount().setPassword("pi");
			employeeInstance.getUserAccount().setEnabled(true);
			employeeInstance.getPrimaryAddress().setStreetAddress("pi");
			employeeInstance.getPrimaryAddress().setCity("zeta");
			employeeInstance.getPrimaryAddress().setZip("delta");
			employeeInstance.getPrimaryAddress().setEmail("Malissa70456");
			employeeInstance.getPrimaryAddress().setCountry("epsilon");
			employeeInstance.getPrimaryAddress().setState("beta");

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
				employee.setLastName("Malissa");
				employee.setDob(dateFormat.parse("2007.11.09 23:27:46 EST"));
				employee.setAge(5646);
				employee.setCode(391);
				employee.getUserAccount().setUsername("Mark4327");
				employee.getUserAccount().setPassword("beta");
				employee.getUserAccount().setEnabled(true);
				employee.getPrimaryAddress().setStreetAddress("Malissa");
				employee.getPrimaryAddress().setCity("Lavendar");
				employee.getPrimaryAddress().setZip("pi");
				employee.getPrimaryAddress().setEmail("delta12465");
				employee.getPrimaryAddress().setCountry("alpha");
				employee.getPrimaryAddress().setState("Wilson");

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

			employee.setFirstName("Mark");
			employee.setLastName("Mark");
			employee.setDob(dateFormat.parse("2007.11.16 15:07:46 EST"));
			employee.setAge(6151);
			employee.setCode(3740);
			employee.getUserAccount().setUsername("beta45959");
			employee.getUserAccount().setPassword("Wilson");
			employee.getUserAccount().setEnabled(true);
			employee.getPrimaryAddress().setStreetAddress("alpha");
			employee.getPrimaryAddress().setCity("Lavendar");
			employee.getPrimaryAddress().setZip("alpha");
			employee.getPrimaryAddress().setEmail("Wilson380");
			employee.getPrimaryAddress().setCountry("zeta");
			employee.getPrimaryAddress().setState("Wilson");

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
