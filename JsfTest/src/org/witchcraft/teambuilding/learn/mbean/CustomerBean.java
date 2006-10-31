package org.witchcraft.teambuilding.learn.mbean;

import javax.faces.model.SelectItem;

import org.witchcraft.teambuilding.learn.bizobjects.Customer;

/**
 * @author Gurpreet
 *
 */
public class CustomerBean {
	
	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	
	private Customer customer;
	
	private SelectItem availableCountries[] = {
			new SelectItem("US"),
			new SelectItem("Italy"),
			new SelectItem("France")						
	};
	
	public CustomerBean(){
		if (this.customer == null)
			customer = new Customer();
	}
	
	/**
	 * This function handles register event , fired from register.jsp
	 * @return
	 */
	public String register(){
		//If some business logic validations fails or some error occures		
		//return FAILURE;
		return SUCCESS;
	
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setCountry(String country) {
		this.customer.setCountry(country);
	}
	
	public SelectItem[] getAvailableCountries() {
		return availableCountries;
	}

}
