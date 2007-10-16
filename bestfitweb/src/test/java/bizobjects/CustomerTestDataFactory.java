package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

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

			customer.setFirstName("Malissa");
			customer.setLastName("alpha");
			customer.setDob(dateFormat.parse("2007.10.04 12:10:07 EDT"));
			customer.setRemarks("Eric");
			customer.getUserAccount().setUsername("delta55291");
			customer.getUserAccount().setPassword("Eric");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("zeta");
			customer.getPrimaryAddress().setCity("Mark");
			customer.getPrimaryAddress().setZip("John");
			customer.getPrimaryAddress().setEmail("Lavendar45231");
			customer.getPrimaryAddress().setCountry("Malissa");
			customer.getPrimaryAddress().setState("epsilon");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerTwo() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Wilson");
			customer.setLastName("beta");
			customer.setDob(dateFormat.parse("2007.10.14 22:21:12 EDT"));
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("Eric73428");
			customer.getUserAccount().setPassword("Lavendar");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("delta");
			customer.getPrimaryAddress().setCity("beta");
			customer.getPrimaryAddress().setZip("Wilson");
			customer.getPrimaryAddress().setEmail("John66234");
			customer.getPrimaryAddress().setCountry("Mark");
			customer.getPrimaryAddress().setState("Mark");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Eric");
			customer.setLastName("Eric");
			customer.setDob(dateFormat.parse("2007.10.21 08:06:47 EDT"));
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("pi4722");
			customer.getUserAccount().setPassword("theta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("delta");
			customer.getPrimaryAddress().setCity("Lavendar");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("alpha47486");
			customer.getPrimaryAddress().setCountry("alpha");
			customer.getPrimaryAddress().setState("epsilon");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFour() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("alpha");
			customer.setLastName("zeta");
			customer.setDob(dateFormat.parse("2007.11.03 22:47:19 EDT"));
			customer.setRemarks("beta");
			customer.getUserAccount().setUsername("Wilson5821");
			customer.getUserAccount().setPassword("epsilon");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("zeta");
			customer.getPrimaryAddress().setCity("gamma");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("alpha11000");
			customer.getPrimaryAddress().setCountry("epsilon");
			customer.getPrimaryAddress().setState("theta");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Eric");
			customer.setLastName("zeta");
			customer.setDob(dateFormat.parse("2007.09.26 01:26:47 EDT"));
			customer.setRemarks("Wilson");
			customer.getUserAccount().setUsername("Eric9608");
			customer.getUserAccount().setPassword("delta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Mark");
			customer.getPrimaryAddress().setCity("Mark");
			customer.getPrimaryAddress().setZip("delta");
			customer.getPrimaryAddress().setEmail("epsilon67012");
			customer.getPrimaryAddress().setCountry("beta");
			customer.getPrimaryAddress().setState("theta");

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
