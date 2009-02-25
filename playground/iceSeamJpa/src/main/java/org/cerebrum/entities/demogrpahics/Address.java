package org.cerebrum.entities.demogrpahics;

import javax.persistence.Embeddable;

import org.jboss.seam.annotations.Name;

@Embeddable
@Name("address")
public class Address {

	private String city;  

	private String state;

	private String country;

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

}
