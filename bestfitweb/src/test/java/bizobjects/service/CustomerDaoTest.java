package bizobjects.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.springframework.test.jpa.AbstractJpaTests;
import org.witchcraft.model.support.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import bizobjects.Customer;

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

			customerInstance.setFirstName("delta");
			customerInstance.setLastName("Wilson");
			customerInstance
					.setDob(dateFormat.parse("2007.10.04 01:28:00 EDT"));
			customerInstance.setRemarks("Malissa");
			customerInstance.getUserAccount().setUsername("alpha25829");
			customerInstance.getUserAccount().setPassword("John");
			customerInstance.getUserAccount().setEnabled(true);
			customerInstance.getPrimaryAddress().setStreetAddress("Mark");
			customerInstance.getPrimaryAddress().setCity("alpha");
			customerInstance.getPrimaryAddress().setZip("alpha");
			customerInstance.getPrimaryAddress().setEmail("epsilon76447");

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

				customer.setFirstName("pi");
				customer.setLastName("epsilon");
				customer.setDob(dateFormat.parse("2007.10.25 22:18:33 EDT"));
				customer.setRemarks("beta");
				customer.getUserAccount().setUsername("Mark92169");
				customer.getUserAccount().setPassword("theta");
				customer.getUserAccount().setEnabled(true);
				customer.getPrimaryAddress().setStreetAddress("gamma");
				customer.getPrimaryAddress().setCity("epsilon");
				customer.getPrimaryAddress().setZip("John");
				customer.getPrimaryAddress().setEmail("alpha65852");

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
			customer.setLastName("theta");
			customer.setDob(dateFormat.parse("2007.09.13 23:01:20 EDT"));
			customer.setRemarks("theta");
			customer.getUserAccount().setUsername("John24853");
			customer.getUserAccount().setPassword("theta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Malissa");
			customer.getPrimaryAddress().setCity("beta");
			customer.getPrimaryAddress().setZip("beta");
			customer.getPrimaryAddress().setEmail("epsilon67742");

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
