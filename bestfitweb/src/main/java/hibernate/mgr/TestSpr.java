package hibernate.mgr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bizobjects.Address;
import bizobjects.Customer;

public class TestSpr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		IBasicController controller = (IBasicController) context.getBean("basicController");
		
		
		Customer customer = new Customer();
		customer.setFirstName("harpreetk");
		Address address = new Address();
		address.setEmail("hrandhawa@yahoo.co.in");
		customer.setPrimaryAddress(address);
		
		controller.save(customer);

	}

}
