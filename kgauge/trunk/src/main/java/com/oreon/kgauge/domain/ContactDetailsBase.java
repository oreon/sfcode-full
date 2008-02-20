
/**
 * This is generated code - to edit code or override methods use - ContactDetails class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public abstract class ContactDetailsBase implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String streetAddress;

	protected String city;

	protected String state;

	protected String country;

	protected String zip;

	protected String phone;

	protected String email;

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	@Column(nullable = false, unique = false)
	public String getCountry() {

		return this.country;
	}

	public String getZip() {
		return this.zip;
	}

	public String getPhone() {
		return this.phone;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {

		return this.email;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract ContactDetails contactDetailsInstance();

}
