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

			customer.setFirstName("Eric");
			customer.setLastName("alpha");
			customer.setDob(dateFormat.parse("2007.09.27 21:09:22 EDT"));
			customer.setRemarks("gamma");
			customer.getUserAccount().setUsername("John1861");
			customer.getUserAccount().setPassword("Mark");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Lavendar");
			customer.getPrimaryAddress().setCity("Lavendar");
			customer.getPrimaryAddress().setZip("beta");
			customer.getPrimaryAddress().setEmail("John4534");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerTwo() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("delta");
			customer.setLastName("beta");
			customer.setDob(dateFormat.parse("2007.10.01 03:55:29 EDT"));
			customer.setRemarks("Wilson");
			customer.getUserAccount().setUsername("delta99157");
			customer.getUserAccount().setPassword("beta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("delta");
			customer.getPrimaryAddress().setCity("Eric");
			customer.getPrimaryAddress().setZip("Wilson");
			customer.getPrimaryAddress().setEmail("gamma60624");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Lavendar");
			customer.setLastName("gamma");
			customer.setDob(dateFormat.parse("2007.10.11 16:12:42 EDT"));
			customer.setRemarks("Lavendar");
			customer.getUserAccount().setUsername("pi26546");
			customer.getUserAccount().setPassword("theta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("alpha");
			customer.getPrimaryAddress().setEmail("theta73208");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFour() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Malissa");
			customer.setLastName("Malissa");
			customer.setDob(dateFormat.parse("2007.09.18 05:12:09 EDT"));
			customer.setRemarks("Mark");
			customer.getUserAccount().setUsername("pi70698");
			customer.getUserAccount().setPassword("theta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Wilson");
			customer.getPrimaryAddress().setCity("pi");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("delta67255");

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
			customer.setLastName("Wilson");
			customer.setDob(dateFormat.parse("2007.09.27 22:11:04 EDT"));
			customer.setRemarks("delta");
			customer.getUserAccount().setUsername("Mark491");
			customer.getUserAccount().setPassword("alpha");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("Wilson");
			customer.getPrimaryAddress().setEmail("Malissa855");

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
