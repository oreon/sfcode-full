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
			customer.setLastName("Wilson");
			customer.setDob(dateFormat.parse("2007.10.16 04:18:09 EDT"));
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("beta7563");
			customer.getUserAccount().setPassword("delta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("delta");
			customer.getPrimaryAddress().setCity("Malissa");
			customer.getPrimaryAddress().setZip("pi");
			customer.getPrimaryAddress().setEmail("Eric29724");
			customer.getPrimaryAddress().setCountry("Malissa");
			customer.getPrimaryAddress().setState("gamma");

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
			customer.setLastName("alpha");
			customer.setDob(dateFormat.parse("2007.09.26 19:18:09 EDT"));
			customer.setRemarks("delta");
			customer.getUserAccount().setUsername("Lavendar44369");
			customer.getUserAccount().setPassword("pi");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("pi");
			customer.getPrimaryAddress().setCity("gamma");
			customer.getPrimaryAddress().setZip("gamma");
			customer.getPrimaryAddress().setEmail("pi73614");
			customer.getPrimaryAddress().setCountry("epsilon");
			customer.getPrimaryAddress().setState("gamma");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Mark");
			customer.setLastName("Eric");
			customer.setDob(dateFormat.parse("2007.10.17 18:19:51 EDT"));
			customer.setRemarks("Wilson");
			customer.getUserAccount().setUsername("Malissa59111");
			customer.getUserAccount().setPassword("zeta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("theta");
			customer.getPrimaryAddress().setCity("John");
			customer.getPrimaryAddress().setZip("Mark");
			customer.getPrimaryAddress().setEmail("beta3241");
			customer.getPrimaryAddress().setCountry("John");
			customer.getPrimaryAddress().setState("delta");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFour() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("beta");
			customer.setLastName("epsilon");
			customer.setDob(dateFormat.parse("2007.10.23 08:16:31 EDT"));
			customer.setRemarks("theta");
			customer.getUserAccount().setUsername("gamma7539");
			customer.getUserAccount().setPassword("zeta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("zeta");
			customer.getPrimaryAddress().setCity("Wilson");
			customer.getPrimaryAddress().setZip("John");
			customer.getPrimaryAddress().setEmail("John99874");
			customer.getPrimaryAddress().setCountry("alpha");
			customer.getPrimaryAddress().setState("Mark");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("theta");
			customer.setLastName("Lavendar");
			customer.setDob(dateFormat.parse("2007.11.09 19:29:51 EST"));
			customer.setRemarks("Mark");
			customer.getUserAccount().setUsername("delta86925");
			customer.getUserAccount().setPassword("John");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("zeta");
			customer.getPrimaryAddress().setCity("Eric");
			customer.getPrimaryAddress().setZip("epsilon");
			customer.getPrimaryAddress().setEmail("Lavendar36864");
			customer.getPrimaryAddress().setCountry("Mark");
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
