package bizobjects.service;

import bizobjects.Customer;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;

public class CustomerDaoTest extends AbstractJpaTests {

	protected Customer customerInstance = new Customer();

	protected CustomerService customerService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	protected TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("customerTestDataFactory");

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

			customerInstance.setFirstName("theta");
			customerInstance.setLastName("Lavendar");
			customerInstance
					.setDob(dateFormat.parse("2007.10.19 12:17:19 EDT"));
			customerInstance.setRemarks("beta");
			customerInstance.getUserAccount().setUsername("gamma39178");
			customerInstance.getUserAccount().setPassword("pi");
			customerInstance.getUserAccount().setEnabled(true);
			customerInstance.getPrimaryAddress().setStreetAddress("Wilson");
			customerInstance.getPrimaryAddress().setCity("zeta");
			customerInstance.getPrimaryAddress().setZip("Wilson");
			customerInstance.getPrimaryAddress().setEmail("pi30745");
			customerInstance.getPrimaryAddress().setCountry("gamma");
			customerInstance.getPrimaryAddress().setState("Malissa");

			customerService.save(customerInstance);
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
			Customer customer = new Customer();

			try {

				customer.setFirstName("Eric");
				customer.setLastName("beta");
				customer.setDob(dateFormat.parse("2007.10.09 09:56:46 EDT"));
				customer.setRemarks("Wilson");
				customer.getUserAccount().setUsername("delta92620");
				customer.getUserAccount().setPassword("pi");
				customer.getUserAccount().setEnabled(true);
				customer.getPrimaryAddress().setStreetAddress("pi");
				customer.getPrimaryAddress().setCity("zeta");
				customer.getPrimaryAddress().setZip("Eric");
				customer.getPrimaryAddress().setEmail("Eric55078");
				customer.getPrimaryAddress().setCountry("Wilson");
				customer.getPrimaryAddress().setState("alpha");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			customerService.save(customer);
			assertNotNull(customer.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Customer customer = (Customer) customerTestDataFactory
					.loadOneRecord();

			customer.setFirstName("alpha");
			customer.setLastName("John");
			customer.setDob(dateFormat.parse("2007.10.24 11:53:26 EDT"));
			customer.setRemarks("Lavendar");
			customer.getUserAccount().setUsername("beta63800");
			customer.getUserAccount().setPassword("gamma");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Lavendar");
			customer.getPrimaryAddress().setCity("Wilson");
			customer.getPrimaryAddress().setZip("beta");
			customer.getPrimaryAddress().setEmail("Wilson99287");
			customer.getPrimaryAddress().setCountry("theta");
			customer.getPrimaryAddress().setState("theta");

			customerService.save(customer);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Customer customer = customerService.load(customerInstance.getId());
			assertNotNull(customer.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByLastName() {
		if (!bTest)
			return;

		List<Customer> customers = customerService
				.findByLastName(customerInstance.getLastName());
		assertTrue(!customers.isEmpty());

		//negative test
		//customers = 
		customerService.findByLastName(customerInstance.getLastName());
		//assertTrue(customers.isEmpty()); 

	}

	public void testFindByUsername() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Customer with username ",
				customerService.findByUsername(customerInstance
						.getUserAccount().getUsername()));
		//assertNull("Found a Customer with username YYY", customerService.findByUsername("YYY"));			

	}

	public void testFindByEmail() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Customer with email ", customerService
				.findByEmail(customerInstance.getPrimaryAddress().getEmail()));
		//assertNull("Found a Customer with email YYY", customerService.findByEmail("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<Customer> customers = customerService
					.searchByExample(customerInstance);
			assertTrue(!customers.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
