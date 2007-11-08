package bizobjects.service;

import bizobjects.Customer;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

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

			customerInstance.setFirstName("beta");
			customerInstance.setLastName("pi");
			customerInstance
					.setDob(dateFormat.parse("2007.10.18 10:32:35 EDT"));
			customerInstance.setAge(6260);
			customerInstance.setRemarks("pi");
			customerInstance.getUserAccount().setUsername("Mark56275");
			customerInstance.getUserAccount().setPassword("Wilson");
			customerInstance.getUserAccount().setEnabled(true);
			customerInstance.getPrimaryAddress().setStreetAddress("zeta");
			customerInstance.getPrimaryAddress().setCity("alpha");
			customerInstance.getPrimaryAddress().setZip("epsilon");
			customerInstance.getPrimaryAddress().setEmail("Lavendar70560");
			customerInstance.getPrimaryAddress().setCountry("Malissa");
			customerInstance.getPrimaryAddress().setState("epsilon");

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

				customer.setFirstName("epsilon");
				customer.setLastName("John");
				customer.setDob(dateFormat.parse("2007.11.25 19:05:55 EST"));
				customer.setAge(8305);
				customer.setRemarks("zeta");
				customer.getUserAccount().setUsername("theta93656");
				customer.getUserAccount().setPassword("Mark");
				customer.getUserAccount().setEnabled(true);
				customer.getPrimaryAddress().setStreetAddress("Wilson");
				customer.getPrimaryAddress().setCity("gamma");
				customer.getPrimaryAddress().setZip("Wilson");
				customer.getPrimaryAddress().setEmail("alpha94626");
				customer.getPrimaryAddress().setCountry("zeta");
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

			customer.setFirstName("John");
			customer.setLastName("alpha");
			customer.setDob(dateFormat.parse("2007.11.26 21:12:35 EST"));
			customer.setAge(3397);
			customer.setRemarks("gamma");
			customer.getUserAccount().setUsername("theta66384");
			customer.getUserAccount().setPassword("Eric");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("gamma");
			customer.getPrimaryAddress().setCity("beta");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("Wilson31948");
			customer.getPrimaryAddress().setCountry("Malissa");
			customer.getPrimaryAddress().setState("Mark");

			customerService.save(customer);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(customerService.getCount() > 0);
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
