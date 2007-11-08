package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

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
			customer.setDob(dateFormat.parse("2007.10.27 08:49:15 EDT"));
			customer.setAge(1595);
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("zeta44399");
			customer.getUserAccount().setPassword("Eric");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("Malissa");
			customer.getPrimaryAddress().setEmail("Mark50850");
			customer.getPrimaryAddress().setCountry("Mark");
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
			customer.setLastName("theta");
			customer.setDob(dateFormat.parse("2007.10.31 09:15:23 EDT"));
			customer.setAge(5293);
			customer.setRemarks("gamma");
			customer.getUserAccount().setUsername("Wilson83084");
			customer.getUserAccount().setPassword("Lavendar");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("delta");
			customer.getPrimaryAddress().setCity("beta");
			customer.getPrimaryAddress().setZip("zeta");
			customer.getPrimaryAddress().setEmail("alpha72863");
			customer.getPrimaryAddress().setCountry("zeta");
			customer.getPrimaryAddress().setState("John");

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
			customer.setLastName("John");
			customer.setDob(dateFormat.parse("2007.11.29 04:07:01 EST"));
			customer.setAge(9996);
			customer.setRemarks("delta");
			customer.getUserAccount().setUsername("John8532");
			customer.getUserAccount().setPassword("gamma");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Wilson");
			customer.getPrimaryAddress().setCity("Wilson");
			customer.getPrimaryAddress().setZip("Wilson");
			customer.getPrimaryAddress().setEmail("John73967");
			customer.getPrimaryAddress().setCountry("Malissa");
			customer.getPrimaryAddress().setState("gamma");

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
			customer.setLastName("Lavendar");
			customer.setDob(dateFormat.parse("2007.12.03 05:25:23 EST"));
			customer.setAge(8923);
			customer.setRemarks("John");
			customer.getUserAccount().setUsername("alpha16952");
			customer.getUserAccount().setPassword("alpha");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Eric");
			customer.getPrimaryAddress().setCity("Mark");
			customer.getPrimaryAddress().setZip("alpha");
			customer.getPrimaryAddress().setEmail("Malissa57888");
			customer.getPrimaryAddress().setCountry("zeta");
			customer.getPrimaryAddress().setState("delta");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("alpha");
			customer.setLastName("beta");
			customer.setDob(dateFormat.parse("2007.11.08 01:34:14 EST"));
			customer.setAge(8938);
			customer.setRemarks("beta");
			customer.getUserAccount().setUsername("alpha3544");
			customer.getUserAccount().setPassword("Lavendar");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("John");
			customer.getPrimaryAddress().setCity("pi");
			customer.getPrimaryAddress().setZip("epsilon");
			customer.getPrimaryAddress().setEmail("epsilon55846");
			customer.getPrimaryAddress().setCountry("zeta");
			customer.getPrimaryAddress().setState("alpha");

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

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Customer customer = createRandomCustomer();
			customerService.save(customer);
		}
	}

	public Customer createRandomCustomer() {
		Customer customer = new Customer();

		customer.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		customer.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		customer.setDob((java.util.Date) RandomValueGeneratorFactory
				.createInstance("java.util.Date"));
		customer.setAge((Integer) RandomValueGeneratorFactory
				.createInstance("int"));
		customer.setRemarks((String) RandomValueGeneratorFactory
				.createInstance("String"));
		customer.getUserAccount().setUsername(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		customer.getUserAccount().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		customer.getUserAccount()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("boolean"));
		customer.getPrimaryAddress().setStreetAddress(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		customer.getPrimaryAddress().setCity(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		customer.getPrimaryAddress().setZip(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		customer.getPrimaryAddress().setEmail(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		customer.getPrimaryAddress().setCountry(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		customer.getPrimaryAddress().setState(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		return customer;
	}

}
