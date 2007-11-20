package com.oreon.jshoppingcart.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.jshoppingcart.service.CustomerService;

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
			customer.setLastName("beta");
			customer.setRemarks("Malissa");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerTwo() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("zeta");
			customer.setLastName("Lavendar");
			customer.setRemarks("beta");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("pi");
			customer.setLastName("epsilon");
			customer.setRemarks("zeta");

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
			customer.setLastName("zeta");
			customer.setRemarks("Wilson");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("zeta");
			customer.setLastName("pi");
			customer.setRemarks("theta");

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
		customer.setRemarks((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return customer;
	}

}
