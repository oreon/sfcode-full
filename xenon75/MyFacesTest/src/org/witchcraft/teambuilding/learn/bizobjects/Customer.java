/**
 * 
 */
package org.witchcraft.teambuilding.learn.bizobjects;

import javax.faces.model.SelectItem;

/**
 * This class represents a customer
 * @author jsingh
 *
 */
public class Customer {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String country;
	
	private SelectItem availableCountries[] = {
			new SelectItem("US"),
			new SelectItem("Italy"),
			new SelectItem("France")						
	};
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public SelectItem[] getAvailableCountries() {
		return availableCountries;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String register() {
		System.out.println("Register!");
		return "success";
	}

}
