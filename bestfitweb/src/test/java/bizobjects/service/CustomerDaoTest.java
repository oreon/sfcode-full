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

			customerInstance.setFirstName("Eric");
			customerInstance.setLastName("beta");
			customerInstance
					.setDob(dateFormat.parse("2007.10.24 20:20:44 EDT"));
			customerInstance.setRemarks("zeta");
			customerInstance.getUserAccount().setUsername("alpha67208");
			customerInstance.getUserAccount().setPassword("John");
			customerInstance.getUserAccount().setEnabled(true);
			customerInstance.getPrimaryAddress().setStreetAddress("Lavendar");
			customerInstance.getPrimaryAddress().setCity("Wilson");
			customerInstance.getPrimaryAddress().setZip("John");
			customerInstance.getPrimaryAddress().setEmail("delta99016");
			customerInstance.getPrimaryAddress().setCountry("alpha");
			customerInstance.getPrimaryAddress().setState("zeta");

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

				customer.setFirstName("delta");
				customer.setLastName("Wilson");
				customer.setDob(dateFormat.parse("2007.10.27 16:43:31 EDT"));
				customer.setRemarks("zeta");
				customer.getUserAccount().setUsername("Lavendar16323");
				customer.getUserAccount().setPassword("gamma");
				customer.getUserAccount().setEnabled(true);
				customer.getPrimaryAddress().setStreetAddress("gamma");
				customer.getPrimaryAddress().setCity("beta");
				customer.getPrimaryAddress().setZip("alpha");
				customer.getPrimaryAddress().setEmail("alpha5760");
				customer.getPrimaryAddress().setCountry("theta");
				customer.getPrimaryAddress().setState("Lavendar");

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

			customer.setFirstName("Lavendar");
			customer.setLastName("delta");
			customer.setDob(dateFormat.parse("2007.09.20 14:17:57 EDT"));
			customer.setRemarks("zeta");
			customer.getUserAccount().setUsername("beta19496");
			customer.getUserAccount().setPassword("delta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Malissa");
			customer.getPrimaryAddress().setCity("John");
			customer.getPrimaryAddress().setZip("John");
			customer.getPrimaryAddress().setEmail("alpha9999");
			customer.getPrimaryAddress().setCountry("Lavendar");
			customer.getPrimaryAddress().setState("Lavendar");

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
