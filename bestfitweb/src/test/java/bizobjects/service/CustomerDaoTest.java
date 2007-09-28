package bizobjects.service;

import bizobjects.Customer;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

public class CustomerDaoTest extends AbstractJpaTests {

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml"};
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {

	}

	public void testSave() {
		//test saving a new record and updating an existing record;
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {
		//return null;
	}

	public void testFindByLastName() {

		List<Customer> customers = customerService.findByLastName("XXX");
		assertTrue(!customers.isEmpty());

		//negative test
		customers = customerService.findByLastName("YYY");
		assertTrue(customers.isEmpty());

	}

	public void testFindByUsername() {

		assertNotNull("Couldn't find a Customer with username XXX",
				customerService.findByUsername("XXX"));
		assertNull("Found a Customer with username YYY", customerService
				.findByUsername("YYY"));

	}

	public void testFindByEmail() {

		assertNotNull("Couldn't find a Customer with email XXX",
				customerService.findByEmail("XXX"));
		assertNull("Found a Customer with email YYY", customerService
				.findByEmail("YYY"));

	}

	public void testSearchByExample() {
		Customer customer = new Customer();
		//customer.setFirstName("Eri");
		List<Customer> customers = customerService.searchByExample(customer);
		assertTrue(!customers.isEmpty());
	}

}
