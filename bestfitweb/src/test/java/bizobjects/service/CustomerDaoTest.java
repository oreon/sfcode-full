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

			customerInstance.setFirstName("Mark");
			customerInstance.setLastName("John");
			customerInstance
					.setDob(dateFormat.parse("2007.11.03 14:37:45 EDT"));
			customerInstance.setAge(5567);
			customerInstance.setRemarks("alpha");
			customerInstance.getUserAccount().setUsername("delta49493");
			customerInstance.getUserAccount().setPassword("alpha");
			customerInstance.getUserAccount().setEnabled(true);
			customerInstance.getPrimaryAddress().setStreetAddress("Lavendar");
			customerInstance.getPrimaryAddress().setCity("delta");
			customerInstance.getPrimaryAddress().setZip("Malissa");
			customerInstance.getPrimaryAddress().setEmail("gamma50672");
			customerInstance.getPrimaryAddress().setCountry("Mark");
			customerInstance.getPrimaryAddress().setState("delta");

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
				customer.setLastName("Malissa");
				customer.setDob(dateFormat.parse("2007.10.23 01:40:32 EDT"));
				customer.setAge(1747);
				customer.setRemarks("Mark");
				customer.getUserAccount().setUsername("zeta76872");
				customer.getUserAccount().setPassword("Wilson");
				customer.getUserAccount().setEnabled(true);
				customer.getPrimaryAddress().setStreetAddress("pi");
				customer.getPrimaryAddress().setCity("Lavendar");
				customer.getPrimaryAddress().setZip("zeta");
				customer.getPrimaryAddress().setEmail("Wilson5318");
				customer.getPrimaryAddress().setCountry("John");
				customer.getPrimaryAddress().setState("zeta");

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
			customer.setLastName("Malissa");
			customer.setDob(dateFormat.parse("2007.11.06 23:14:58 EST"));
			customer.setAge(7165);
			customer.setRemarks("theta");
			customer.getUserAccount().setUsername("theta83704");
			customer.getUserAccount().setPassword("Malissa");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("epsilon");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("theta39223");
			customer.getPrimaryAddress().setCountry("alpha");
			customer.getPrimaryAddress().setState("gamma");

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
