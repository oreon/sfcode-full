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

			customer.setFirstName("Mark");
			customer.setLastName("theta");
			customer.setDob(dateFormat.parse("2007.09.25 22:18:30 EDT"));
			customer.setRemarks("theta");
			customer.getUserAccount().setUsername("pi87929");
			customer.getUserAccount().setPassword("beta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("gamma");
			customer.getPrimaryAddress().setCity("alpha");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("Mark56132");
			customer.getPrimaryAddress().setCountry("Wilson");
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

			customer.setFirstName("Malissa");
			customer.setLastName("Malissa");
			customer.setDob(dateFormat.parse("2007.10.15 06:21:17 EDT"));
			customer.setRemarks("Malissa");
			customer.getUserAccount().setUsername("beta86708");
			customer.getUserAccount().setPassword("beta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Lavendar");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("gamma");
			customer.getPrimaryAddress().setEmail("alpha35270");
			customer.getPrimaryAddress().setCountry("theta");
			customer.getPrimaryAddress().setState("beta");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("epsilon");
			customer.setLastName("John");
			customer.setDob(dateFormat.parse("2007.10.04 22:06:15 EDT"));
			customer.setRemarks("pi");
			customer.getUserAccount().setUsername("alpha61737");
			customer.getUserAccount().setPassword("Lavendar");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Lavendar");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("alpha34320");
			customer.getPrimaryAddress().setCountry("gamma");
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

			customer.setFirstName("theta");
			customer.setLastName("pi");
			customer.setDob(dateFormat.parse("2007.10.06 01:37:57 EDT"));
			customer.setRemarks("beta");
			customer.getUserAccount().setUsername("Malissa61296");
			customer.getUserAccount().setPassword("John");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Wilson");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("alpha8840");
			customer.getPrimaryAddress().setCountry("John");
			customer.getPrimaryAddress().setState("John");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Wilson");
			customer.setLastName("Eric");
			customer.setDob(dateFormat.parse("2007.09.24 20:45:10 EDT"));
			customer.setRemarks("pi");
			customer.getUserAccount().setUsername("alpha31084");
			customer.getUserAccount().setPassword("gamma");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("delta");
			customer.getPrimaryAddress().setCity("John");
			customer.getPrimaryAddress().setZip("Wilson");
			customer.getPrimaryAddress().setEmail("alpha78242");
			customer.getPrimaryAddress().setCountry("alpha");
			customer.getPrimaryAddress().setState("pi");

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
