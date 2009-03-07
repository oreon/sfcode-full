package org.cerebrum.domain;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.witchcraft.base.entity.BusinessEntity;

@MappedSuperclass
public class Person extends BusinessEntity {

	@NotNull
	@Length(min = 2)
	protected String firstName;

	@NotNull
	@Length(min = 2)
	protected String lastName;

	@NotNull
	private String email;

	private String city = "Toronto";

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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

}
