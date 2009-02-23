package org.cerebrum.entities.demogrpahics;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Person extends BusinessEntity {

	private String firstName;

	private String lastName;

	private Address address;

	private Gender gender;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

}
