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

			customer.setFirstName("zeta");
			customer.setLastName("delta");
			customer.setDob(dateFormat.parse("2007.11.29 04:11:28 EST"));
			customer.setAge(6089);
			customer.setRemarks("delta");
			customer.getUserAccount().setUsername("Wilson57186");
			customer.getUserAccount().setPassword("Malissa");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Mark");
			customer.getPrimaryAddress().setCity("delta");
			customer.getPrimaryAddress().setZip("Malissa");
			customer.getPrimaryAddress().setEmail("beta78355");
			customer.getPrimaryAddress().setCountry("John");
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

			customer.setFirstName("epsilon");
			customer.setLastName("Lavendar");
			customer.setDob(dateFormat.parse("2007.11.08 20:25:21 EST"));
			customer.setAge(8540);
			customer.setRemarks("Wilson");
			customer.getUserAccount().setUsername("theta21114");
			customer.getUserAccount().setPassword("pi");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Lavendar");
			customer.getPrimaryAddress().setCity("pi");
			customer.getPrimaryAddress().setZip("Malissa");
			customer.getPrimaryAddress().setEmail("Mark94742");
			customer.getPrimaryAddress().setCountry("John");
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

			customer.setFirstName("Mark");
			customer.setLastName("gamma");
			customer.setDob(dateFormat.parse("2007.11.21 02:38:41 EST"));
			customer.setAge(7691);
			customer.setRemarks("gamma");
			customer.getUserAccount().setUsername("Wilson3357");
			customer.getUserAccount().setPassword("Mark");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("gamma");
			customer.getPrimaryAddress().setCity("gamma");
			customer.getPrimaryAddress().setZip("Eric");
			customer.getPrimaryAddress().setEmail("Malissa99467");
			customer.getPrimaryAddress().setCountry("Lavendar");
			customer.getPrimaryAddress().setState("pi");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFour() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("Eric");
			customer.setLastName("delta");
			customer.setDob(dateFormat.parse("2007.11.03 17:38:08 EDT"));
			customer.setAge(1959);
			customer.setRemarks("gamma");
			customer.getUserAccount().setUsername("zeta77219");
			customer.getUserAccount().setPassword("zeta");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("theta");
			customer.getPrimaryAddress().setCity("John");
			customer.getPrimaryAddress().setZip("Lavendar");
			customer.getPrimaryAddress().setEmail("beta95415");
			customer.getPrimaryAddress().setCountry("John");
			customer.getPrimaryAddress().setState("Wilson");

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
			customer.setLastName("zeta");
			customer.setDob(dateFormat.parse("2007.12.01 11:35:21 EST"));
			customer.setAge(862);
			customer.setRemarks("Mark");
			customer.getUserAccount().setUsername("alpha71198");
			customer.getUserAccount().setPassword("pi");
			customer.getUserAccount().setEnabled(true);
			customer.getPrimaryAddress().setStreetAddress("Eric");
			customer.getPrimaryAddress().setCity("pi");
			customer.getPrimaryAddress().setZip("Lavendar");
			customer.getPrimaryAddress().setEmail("epsilon40031");
			customer.getPrimaryAddress().setCountry("Malissa");
			customer.getPrimaryAddress().setState("beta");

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
