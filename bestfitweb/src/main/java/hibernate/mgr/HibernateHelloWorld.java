package hibernate.mgr;

import org.hibernate.*;

import bizobjects.Address;
import bizobjects.Customer;


public class HibernateHelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-gene
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setFirstName("harpreet");
		Address address = new Address();
		address.setEmail("hrandhawa@yahoo.com");
		customer.setPrimaryAddress(address);
		
		session.save(customer);
		
		tx.commit();
		session.close();
		
		HibernateUtil.shutdown();
	}

}
