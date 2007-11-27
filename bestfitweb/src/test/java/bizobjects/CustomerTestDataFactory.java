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

			customer.setFirstName("theta");
			customer.setLastName("beta");
			customer.setDob(dateFormat.parse("2007.10.29 17:14:58 EDT"));
			customer.setAge(9789);
			customer.setRemarks("Mark");
			customer.getUserAccount().setUsername("alpha12843");
			customer.getUserAccount().setPassword("theta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("John");
			customer.getPrimaryAddress().setCity("pi");
			customer.getPrimaryAddress().setZip("gamma");
			customer.getPrimaryAddress().setEmail("beta53816");
			customer.getPrimaryAddress().setCountry("pi");
			customer.getPrimaryAddress().setState("zeta");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerTwo() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Eric");
			customer.setLastName("epsilon");
			customer.setDob(dateFormat.parse("2007.10.20 13:46:04 EDT"));
			customer.setAge(6300);
			customer.setRemarks("epsilon");
			customer.getUserAccount().setUsername("Wilson55802");
			customer.getUserAccount().setPassword("theta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Lavendar");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("Wilson");
			customer.getPrimaryAddress().setEmail("pi57925");
			customer.getPrimaryAddress().setCountry("epsilon");
			customer.getPrimaryAddress().setState("theta");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("beta");
			customer.setLastName("Mark");
			customer.setDob(dateFormat.parse("2007.10.23 10:32:11 EDT"));
			customer.setAge(4311);
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("Lavendar6311");
			customer.getUserAccount().setPassword("beta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Eric");
			customer.getPrimaryAddress().setCity("Wilson");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("epsilon52180");
			customer.getPrimaryAddress().setCountry("epsilon");
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
			customer.setLastName("alpha");
			customer.setDob(dateFormat.parse("2007.10.28 01:06:04 EDT"));
			customer.setAge(9040);
			customer.setRemarks("Eric");
			customer.getUserAccount().setUsername("Malissa33437");
			customer.getUserAccount().setPassword("Eric");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Eric");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("gamma55899");
			customer.getPrimaryAddress().setCountry("theta");
			customer.getPrimaryAddress().setState("epsilon");

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
			customer.setLastName("Lavendar");
			customer.setDob(dateFormat.parse("2007.10.15 22:43:53 EDT"));
			customer.setAge(1704);
			customer.setRemarks("Malissa");
			customer.getUserAccount().setUsername("pi16571");
			customer.getUserAccount().setPassword("Eric");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Malissa");
			customer.getPrimaryAddress().setCity("Malissa");
			customer.getPrimaryAddress().setZip("zeta");
			customer.getPrimaryAddress().setEmail("John69833");
			customer.getPrimaryAddress().setCountry("Mark");
			customer.getPrimaryAddress().setState("epsilon");

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
