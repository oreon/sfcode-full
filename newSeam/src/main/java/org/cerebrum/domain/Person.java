package org.cerebrum.domain;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.base.entity.Unique;

@MappedSuperclass
public class Person extends BusinessEntity {

	@NotNull
	@Length(min = 2)
	protected String firstName;

	@NotNull
	@Length(min = 2)
	protected String lastName;

	@NotNull
	//@Unique
	protected String email;

	protected String city = "Toronto";

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

	

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

}
