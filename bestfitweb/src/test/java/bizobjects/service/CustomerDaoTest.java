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

			customerInstance.setFirstName("epsilon");
			customerInstance.setLastName("Wilson");
			customerInstance
					.setDob(dateFormat.parse("2007.10.25 12:49:14 EDT"));
			customerInstance.setAge(2123);
			customerInstance.setRemarks("gamma");
			customerInstance.getUserAccount().setUsername("Mark29738");
			customerInstance.getUserAccount().setPassword("theta");
			customerInstance.getUserAccount().setEnabled(true);
			customerInstance.getPrimaryAddress().setStreetAddress("epsilon");
			customerInstance.getPrimaryAddress().setCity("Mark");
			customerInstance.getPrimaryAddress().setZip("pi");
			customerInstance.getPrimaryAddress().setEmail("delta42684");
			customerInstance.getPrimaryAddress().setCountry("zeta");
			customerInstance.getPrimaryAddress().setState("pi");

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

				customer.setFirstName("Mark");
				customer.setLastName("Eric");
				customer.setDob(dateFormat.parse("2007.11.10 19:25:21 EST"));
				customer.setAge(8373);
				customer.setRemarks("alpha");
				customer.getUserAccount().setUsername("Wilson56695");
				customer.getUserAccount().setPassword("zeta");
				customer.getUserAccount().setEnabled(true);
				customer.getPrimaryAddress().setStreetAddress("gamma");
				customer.getPrimaryAddress().setCity("zeta");
				customer.getPrimaryAddress().setZip("gamma");
				customer.getPrimaryAddress().setEmail("Lavendar67335");
				customer.getPrimaryAddress().setCountry("zeta");
				customer.getPrimaryAddress().setState("delta");

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

			customer.setFirstName("Mark");
			customer.setLastName("Eric");
			customer.setDob(dateFormat.parse("2007.10.20 19:41:28 EDT"));
			customer.setAge(7953);
			customer.setRemarks("John");
			customer.getUserAccount().setUsername("Malissa13106");
			customer.getUserAccount().setPassword("epsilon");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("John");
			customer.getPrimaryAddress().setCity("Mark");
			customer.getPrimaryAddress().setZip("alpha");
			customer.getPrimaryAddress().setEmail("Lavendar9455");
			customer.getPrimaryAddress().setCountry("Mark");
			customer.getPrimaryAddress().setState("alpha");

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
