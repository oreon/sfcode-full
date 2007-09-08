package com.oreon.bestfit;

import java.util.ArrayList;
import java.util.List;

import bizobjects.Customer;


public class CustomerBackingBean {
    private Customer customer = new Customer();
	

    public Customer getCustomer() {
        return customer;
    }

    public void set(Customer customer) {
        this.customer = customer;
    }

    public String update() {
        return "success";
    }
    
    private long count;
    
    /**Get a list of all 
     * @return
     */
    public List<Customer> getCustomers(){
    	List<Customer> customers = new ArrayList<Customer>();
    	
    	customers.add(createCustomer("Eric", "Regis"));
    	customers.add(createCustomer("Huy", "Mokys"));
    	customers.add(createCustomer("Levi", "Mokys"));
    	customers.add(createCustomer("Sukh", "Bal"));
    	customers.add(createCustomer("Amrita", "Bal"));
    
    	return customers;
    }
    
    
 
    	 
    /**
     * @param fn
     * @param ln
     * @return
     */
    private Customer createCustomer(String fn, String ln){
    	Customer cust = new Customer();
    	cust.setId(++count);
    	cust.setFirstName(fn);
    	cust.setLastName(ln);
    	cust.getPrimaryAddress().setCity("Burlington");
    	cust.getPrimaryAddress().setEmail("ton@yahoo.com");
    	return cust;
    }
}
