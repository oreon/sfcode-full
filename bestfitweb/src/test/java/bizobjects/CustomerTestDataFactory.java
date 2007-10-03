package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import org.witchcraft.model.support.TestDataFactory;

import org.springframework.transaction.annotation.Transactional;

import bizobjects.service.CustomerService;

@Transactional
public class CustomerTestDataFactory extends AbstractTestDataFactory<Customer> {

	List<Customer> customers = new ArrayList<Customer>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void register(Customer customer) {
		customers.add(customer);
	}

	public Customer createCustomerOne() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("epsilon");
			customer.setLastName("John");
			customer.setDob(dateFormat.parse("2007.09.15 10:03:06 EDT"));
			customer.setRemarks("Eric");
			customer.getUserAccount().setUsername("theta13651");
			customer.getUserAccount().setPassword("John");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("alpha");
			customer.getPrimaryAddress().setCity("alpha");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("theta43626");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerTwo() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("epsilon");
			customer.setLastName("John");
			customer.setDob(dateFormat.parse("2007.10.16 15:19:13 EDT"));
			customer.setRemarks("zeta");
			customer.getUserAccount().setUsername("Malissa57274");
			customer.getUserAccount().setPassword("Lavendar");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("zeta");
			customer.getPrimaryAddress().setCity("Mark");
			customer.getPrimaryAddress().setZip("Mark");
			customer.getPrimaryAddress().setEmail("Eric4431");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("gamma");
			customer.setLastName("Eric");
			customer.setDob(dateFormat.parse("2007.10.25 13:55:53 EDT"));
			customer.setRemarks("pi");
			customer.getUserAccount().setUsername("beta53754");
			customer.getUserAccount().setPassword("John");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Mark");
			customer.getPrimaryAddress().setCity("theta");
			customer.getPrimaryAddress().setZip("delta");
			customer.getPrimaryAddress().setEmail("pi78340");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFour() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("gamma");
			customer.setLastName("Malissa");
			customer.setDob(dateFormat.parse("2007.10.20 02:29:13 EDT"));
			customer.setRemarks("beta");
			customer.getUserAccount().setUsername("beta11575");
			customer.getUserAccount().setPassword("pi");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Malissa");
			customer.getPrimaryAddress().setCity("Eric");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("epsilon46714");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("epsilon");
			customer.setLastName("epsilon");
			customer.setDob(dateFormat.parse("2007.09.15 16:37:31 EDT"));
			customer.setRemarks("beta");
			customer.getUserAccount().setUsername("gamma14482");
			customer.getUserAccount().setPassword("epsilon");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Wilson");
			customer.getPrimaryAddress().setCity("John");
			customer.getPrimaryAddress().setZip("zeta");
			customer.getPrimaryAddress().setEmail("theta69667");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer loadOneRecord() {
		List<Customer> customers = customerService.loadAll();

		if (customers.isEmpty()) {
			persistAll();
			customers = customerService.loadAll();
		}

		return customers.get(new Random().nextInt(customers.size()));
	}

	public List<Customer> getAllAsList() {

		if (customers.isEmpty()) {
			createCustomerOne();
			createCustomerTwo();
			createCustomerThree();
			createCustomerFour();
			createCustomerFive();

		}

		return customers;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Customer customer : customers) {
			customerService.save(customer);
		}

		alreadyPersisted = true;
	}

}
