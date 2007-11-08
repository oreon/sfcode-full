
/**
 * This is generated code - to edit code or override methods use - Person class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.olympics.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class PersonBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String firstName;

	protected String lastName;

	protected Date dob;

	protected Gender gender;

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Date getDob() {
		return this.dob;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	private com.oreon.olympics.domain.Country country;

	public void setCountry(com.oreon.olympics.domain.Country country) {
		this.country = country;
	}

	@ManyToOne
	@JoinColumn(name = "country_ID", nullable = false)
	public com.oreon.olympics.domain.Country getCountry() {
		return this.country;
	}

	public abstract Person personInstance();

}
