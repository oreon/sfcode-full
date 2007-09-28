package testdata;

import bizobjects.Customer;
import bizobjects.OrderStatus;

import bizobjects.Employee;

import bizobjects.PlacedOrder;

import bizobjects.Product;

import bizobjects.OrderItem;
import bizobjects.service.CustomerService;
import bizobjects.service.OrderItemService;
import bizobjects.service.PlacedOrderService;
import bizobjects.service.ProductService;

import usermanagement.User;

import usermanagement.Authority;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;
import java.util.Random;

import java.text.SimpleDateFormat;

public class TestDataFactory {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");
	
	protected static ApplicationContext context;
	
	public TestDataFactory(){
		context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
	}

	public static Customer createCustomerOne() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("beta");
			customer.setLastName("gamma");
			customer.setDob(dateFormat.parse("2007.10.03 12:38:41 EDT"));
			customer.setRemarks("beta");
			customer.getUserAccount().setUsername("zeta18832");
			customer.getUserAccount().setPassword("zeta");
			customer.getPrimaryAddress().setStreetAddress("alpha");
			customer.getPrimaryAddress().setCity("pi");
			customer.getPrimaryAddress().setZip("alpha");
			customer.getPrimaryAddress().setEmail("beta46200");

			

			//Give the user an authority
			//createAuthorityForCustomer(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public static Customer createCustomerTwo() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("delta");
			customer.setLastName("alpha");
			customer.setDob(dateFormat.parse("2007.10.18 18:33:43 EDT"));
			customer.setRemarks("zeta");
			customer.getUserAccount().setUsername("epsilon69773");
			customer.getUserAccount().setPassword("delta");
			customer.getPrimaryAddress().setStreetAddress("pi");
			customer.getPrimaryAddress().setCity("epsilon");
			customer.getPrimaryAddress().setZip("pi");
			customer.getPrimaryAddress().setEmail("theta41921");

			

			//Give the user an authority
			//createAuthorityForCustomer(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public static Customer createCustomerThree() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("beta");
			customer.setLastName("beta");
			customer.setDob(dateFormat.parse("2007.10.15 06:45:21 EDT"));
			customer.setRemarks("pi");
			customer.getUserAccount().setUsername("epsilon44894");
			customer.getUserAccount().setPassword("zeta");
			customer.getPrimaryAddress().setStreetAddress("beta");
			customer.getPrimaryAddress().setCity("zeta");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("pi87462");

			

			//Give the user an authority
			//createAuthorityForCustomer(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public static Customer createCustomerFour() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("alpha");
			customer.setLastName("pi");
			customer.setDob(dateFormat.parse("2007.09.16 11:15:54 EDT"));
			customer.setRemarks("theta");
			customer.getUserAccount().setUsername("gamma59954");
			customer.getUserAccount().setPassword("pi");
			customer.getPrimaryAddress().setStreetAddress("beta");
			customer.getPrimaryAddress().setCity("beta");
			customer.getPrimaryAddress().setZip("theta");
			customer.getPrimaryAddress().setEmail("gamma79685");

			

			//Give the user an authority
			//createAuthorityForCustomer(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public static Customer createCustomerFive() {
		Customer customer = new Customer();

		try {

			customer.setFirstName("alpha");
			customer.setLastName("theta");
			customer.setDob(dateFormat.parse("2007.09.21 10:59:14 EDT"));
			customer.setRemarks("alpha");
			customer.getUserAccount().setUsername("zeta42197");
			customer.getUserAccount().setPassword("zeta");
			customer.getPrimaryAddress().setStreetAddress("alpha");
			customer.getPrimaryAddress().setCity("epsilon");
			customer.getPrimaryAddress().setZip("epsilon");
			customer.getPrimaryAddress().setEmail("pi62219");

			

			//Give the user an authority
			//createAuthorityForCustomer(customer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}
	
	public static void persistCustomers(){
		CustomerService customerService = (CustomerService) context.getBean("customerService");
		customerService.save(createCustomerOne());
		customerService.save(createCustomerTwo());
		customerService.save(createCustomerThree());
		customerService.save(createCustomerFour());
	}

	public static Customer loadCustomer() {
		CustomerService customerService = (CustomerService) context.getBean("customerService");
		List<Customer> customers = customerService.loadAll();
		
		if(customers.isEmpty()){
			persistCustomers();
			customers = customerService.loadAll();
		}
		
		return customers.get(new Random().nextInt(customers.size()));
	}

	/*
		public Authority createAuthorityForCustomer(Customer customer) { 
			Authority authroirty = new Authority(customer, "role_customer" );	
			return authroirty;
		}
	 */

	public static Employee createEmployeeOne() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("epsilon");
			employee.setLastName("theta");
			employee.setDob(dateFormat.parse("2007.09.19 19:03:06 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("beta25671");
			employee.getUserAccount().setPassword("epsilon");
			employee.getPrimaryAddress().setStreetAddress("zeta");
			employee.getPrimaryAddress().setCity("zeta");
			employee.getPrimaryAddress().setZip("alpha");
			employee.getPrimaryAddress().setEmail("alpha25634");

			employee.setUserAccount(loadUser());

			//Give the user an authority
			//createAuthorityForEmployee(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public static Employee createEmployeeTwo() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("zeta");
			employee.setLastName("beta");
			employee.setDob(dateFormat.parse("2007.09.16 15:55:54 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("delta85359");
			employee.getUserAccount().setPassword("zeta");
			employee.getPrimaryAddress().setStreetAddress("beta");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("theta");
			employee.getPrimaryAddress().setEmail("epsilon15169");

			employee.setUserAccount(loadUser());

			//Give the user an authority
			//createAuthorityForEmployee(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public static Employee createEmployeeThree() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("epsilon");
			employee.setLastName("pi");
			employee.setDob(dateFormat.parse("2007.09.12 16:59:46 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("pi35754");
			employee.getUserAccount().setPassword("pi");
			employee.getPrimaryAddress().setStreetAddress("theta");
			employee.getPrimaryAddress().setCity("zeta");
			employee.getPrimaryAddress().setZip("gamma");
			employee.getPrimaryAddress().setEmail("gamma61519");

			employee.setUserAccount(loadUser());

			//Give the user an authority
			//createAuthorityForEmployee(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public static Employee createEmployeeFour() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("epsilon");
			employee.setLastName("zeta");
			employee.setDob(dateFormat.parse("2007.09.23 13:42:34 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("pi375");
			employee.getUserAccount().setPassword("delta");
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("pi");
			employee.getPrimaryAddress().setZip("beta");
			employee.getPrimaryAddress().setEmail("theta52310");

			employee.setUserAccount(loadUser());

			//Give the user an authority
			//createAuthorityForEmployee(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public static Employee createEmployeeFive() {
		Employee employee = new Employee();

		try {

			employee.setFirstName("beta");
			employee.setLastName("gamma");
			employee.setDob(dateFormat.parse("2007.09.09 02:54:48 EDT"));
			employee.setCode(719);
			employee.getUserAccount().setUsername("theta29814");
			employee.getUserAccount().setPassword("alpha");
			employee.getPrimaryAddress().setStreetAddress("epsilon");
			employee.getPrimaryAddress().setCity("epsilon");
			employee.getPrimaryAddress().setZip("gamma");
			employee.getPrimaryAddress().setEmail("beta41071");

			employee.setUserAccount(loadUser());

			//Give the user an authority
			//createAuthorityForEmployee(employee);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employee;
	}

	public static Employee loadEmployee() {
		Employee employee = employeeService.load(1L);
		return employee;
	}

	/*
		public Authority createAuthorityForEmployee(Employee employee) { 
			Authority authroirty = new Authority(employee, "role_employee" );	
			return authroirty;
		}
	 */

	public static PlacedOrder createPlacedOrderOne() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("delta");
			placedOrder.setPaymentMethod("epsilon");
			placedOrder.setStatus(OrderStatus.NEW);

			placedOrder.setCustomer(loadCustomer());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public static PlacedOrder createPlacedOrderTwo() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("zeta");
			placedOrder.setPaymentMethod("zeta");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			placedOrder.setCustomer(loadCustomer());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public static PlacedOrder createPlacedOrderThree() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("gamma");
			placedOrder.setPaymentMethod("zeta");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			placedOrder.setCustomer(loadCustomer());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public static PlacedOrder createPlacedOrderFour() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("delta");
			placedOrder.setPaymentMethod("theta");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			placedOrder.setCustomer(loadCustomer());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public static PlacedOrder createPlacedOrderFive() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("theta");
			placedOrder.setPaymentMethod("gamma");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			placedOrder.setCustomer(loadCustomer());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public static PlacedOrder loadPlacedOrder() {
		PlacedOrder placedOrder = placedOrderService.load(1L);
		return placedOrder;
	}

	public static Product createProductOne() {
		Product product = new Product();

		try {

			product.setName("theta");
			product.setBrand("delta");
			product.setListPrice(2.454532713340274);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public static Product createProductTwo() {
		Product product = new Product();

		try {

			product.setName("zeta");
			product.setBrand("delta");
			product.setListPrice(2.454532713340274);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public static Product createProductThree() {
		Product product = new Product();

		try {

			product.setName("alpha");
			product.setBrand("pi");
			product.setListPrice(2.454532713340274);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public static Product createProductFour() {
		Product product = new Product();

		try {

			product.setName("zeta");
			product.setBrand("epsilon");
			product.setListPrice(2.454532713340274);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public static Product createProductFive() {
		Product product = new Product();

		try {

			product.setName("beta");
			product.setBrand("zeta");
			product.setListPrice(2.454532713340274);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public static Product loadProduct() {
		ProductService customerService = (ProductService) context.getBean("productService");
		List<Product> customers = customerService.loadAll();
		
		if(customers.isEmpty()){
			persistProducts();
			customers = customerService.loadAll();
		}
		
		return customers.get(new Random().nextInt(customers.size()));
	}

	private static void persistProducts() {
		ProductService service = (ProductService) context.getBean("productService");
		service.save(createProductFive());
		service.save(createProductThree());
		service.save(createProductTwo());
		
	}

	public static OrderItem createOrderItemOne() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			orderItem.setProduct(loadProduct());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public static OrderItem createOrderItemTwo() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			orderItem.setProduct(loadProduct());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public static OrderItem createOrderItemThree() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			orderItem.setProduct(loadProduct());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public static OrderItem createOrderItemFour() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			orderItem.setProduct(loadProduct());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public static OrderItem createOrderItemFive() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			orderItem.setProduct(loadProduct());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public static OrderItem loadOrderItem() {
		OrderItem orderItem = orderItemService.load(1L);
		return orderItem;
	}

	public static User createUserOne() {
		User user = new User();

		try {

			user.setUsername("zeta28514");
			user.setPassword("epsilon");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public static User createUserTwo() {
		User user = new User();

		try {

			user.setUsername("alpha56256");
			user.setPassword("beta");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public static User createUserThree() {
		User user = new User();

		try {

			user.setUsername("beta95796");
			user.setPassword("gamma");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public static User createUserFour() {
		User user = new User();

		try {

			user.setUsername("zeta66734");
			user.setPassword("theta");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public static User createUserFive() {
		User user = new User();

		try {

			user.setUsername("epsilon10301");
			user.setPassword("epsilon");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public static User loadUser() {
		User user = userService.load(1L);
		return user;
	}

	public static Authority createAuthorityOne() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("pi");

			authority.setUser(loadUser());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public static Authority createAuthorityTwo() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("delta");

			authority.setUser(loadUser());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public static Authority createAuthorityThree() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("delta");

			authority.setUser(loadUser());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public static Authority createAuthorityFour() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("pi");

			authority.setUser(loadUser());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public static Authority createAuthorityFive() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("beta");

			authority.setUser(loadUser());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public static Authority loadAuthority() {
		Authority authority = authorityService.load(1L);
		return authority;
	}

	public void persistOrderItems() {
		OrderItemService service = (OrderItemService) context.getBean("orderItemService");
		service.save(createOrderItemFive());
		service.save(createOrderItemOne());
		service.save(createOrderItemTwo());
		service.save(createOrderItemThree());
	}
	
	public void persistOrders(){
		PlacedOrderService poService = (PlacedOrderService) context.getBean("placedOrderService");
		poService.save(createPlacedOrderFive());
		poService.save(createPlacedOrderOne());
		poService.save(createPlacedOrderThree());
	}

}
