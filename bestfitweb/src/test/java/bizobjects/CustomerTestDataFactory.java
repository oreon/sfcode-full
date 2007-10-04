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

			customer.setFirstName("Malissa");
			customer.setLastName("pi");
			customer.setDob(dateFormat.parse("2007.10.06 14:00:16 EDT"));
			customer.setRemarks("Wilson");
			customer.getUserAccount().setUsername("Malissa30896");
			customer.getUserAccount().setPassword("Wilson");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("Malissa36429");

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
			customer.setDob(dateFormat.parse("2007.10.25 18:02:31 EDT"));
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("delta94310");
			customer.getUserAccount().setPassword("zeta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("Lavendar");
			customer.getPrimaryAddress().setEmail("Lavendar78146");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Malissa");
			customer.setLastName("alpha");
			customer.setDob(dateFormat.parse("2007.09.20 17:40:49 EDT"));
			customer.setRemarks("epsilon");
			customer.getUserAccount().setUsername("delta78923");
			customer.getUserAccount().setPassword("epsilon");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Wilson");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("alpha");
			customer.getPrimaryAddress().setEmail("alpha2166");

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
			customer.setLastName("Malissa");
			customer.setDob(dateFormat.parse("2007.10.11 13:41:58 EDT"));
			customer.setRemarks("gamma");
			customer.getUserAccount().setUsername("Mark30707");
			customer.getUserAccount().setPassword("Eric");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Mark");
			customer.getPrimaryAddress().setCity("John");
			customer.getPrimaryAddress().setZip("beta");
			customer.getPrimaryAddress().setEmail("John40407");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("beta");
			customer.setLastName("theta");
			customer.setDob(dateFormat.parse("2007.10.27 10:54:45 EDT"));
			customer.setRemarks("Lavendar");
			customer.getUserAccount().setUsername("theta64872");
			customer.getUserAccount().setPassword("alpha");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("John");
			customer.getPrimaryAddress().setZip("delta");
			customer.getPrimaryAddress().setEmail("John71248");

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
