package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import bizobjects.service.CustomerService;

public class CustomerTestDataFactory extends AbstractTestDataFactory {

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

			customer.setFirstName("alpha");
			customer.setLastName("epsilon");
			customer.setDob(dateFormat.parse("2007.10.19 08:47:57 EDT"));
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("alpha99105");
			customer.getUserAccount().setPassword("delta");
			customer.getPrimaryAddress().setStreetAddress("zeta");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("gamma85046");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerTwo() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("beta");
			customer.setLastName("pi");
			customer.setDob(dateFormat.parse("2007.10.21 14:41:17 EDT"));
			customer.setRemarks("epsilon");
			customer.getUserAccount().setUsername("gamma65455");
			customer.getUserAccount().setPassword("epsilon");
			customer.getPrimaryAddress().setStreetAddress("gamma");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("beta");
			customer.getPrimaryAddress().setEmail("beta69186");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("delta");
			customer.setLastName("epsilon");
			customer.setDob(dateFormat.parse("2007.09.26 09:07:57 EDT"));
			customer.setRemarks("beta");
			customer.getUserAccount().setUsername("alpha33020");
			customer.getUserAccount().setPassword("delta");
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("pi");
			customer.getPrimaryAddress().setZip("zeta");
			customer.getPrimaryAddress().setEmail("pi4137");

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
			customer.setLastName("epsilon");
			customer.setDob(dateFormat.parse("2007.10.01 20:24:37 EDT"));
			customer.setRemarks("zeta");
			customer.getUserAccount().setUsername("theta3688");
			customer.getUserAccount().setPassword("alpha");
			customer.getPrimaryAddress().setStreetAddress("epsilon");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("gamma");
			customer.getPrimaryAddress().setEmail("zeta47472");

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
			customer.setLastName("theta");
			customer.setDob(dateFormat.parse("2007.09.14 19:04:04 EDT"));
			customer.setRemarks("pi");
			customer.getUserAccount().setUsername("delta85510");
			customer.getUserAccount().setPassword("theta");
			customer.getPrimaryAddress().setStreetAddress("delta");
			customer.getPrimaryAddress().setCity("theta");
			customer.getPrimaryAddress().setZip("epsilon");
			customer.getPrimaryAddress().setEmail("alpha73215");

			register(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public Customer loadCustomer() {
		List<Customer> customers = customerService.loadAll();

		if (customers.isEmpty()) {
			persistAll();
			customers = customerService.loadAll();
		}

		return customers.get(new Random().nextInt(customers.size()));
	}

	List<Customer> getAllAsList() {

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
		if (!isPersistable())
			return;

		getAllAsList();
		for (Customer customer : customers) {
			customerService.save(customer);
		}
	}

	/** Will return a random number of PlacedOrders
	 * @return
	 */
	List<Customer> getFew() {
		List<Customer> all = getAllAsList();
		int numToChoose = new Random(1212343).nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<Customer>();
		List returnList = new ArrayList<Customer>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random(1212343).nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(numToChoose);
		}

		return returnList;
	}

}
