package org.cerebrum.domain.demographics;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@MappedSuperclass
public class Person extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String firstName;

	protected Date dateOfBirth;

	protected Gender gender;

	@Transient
	protected Integer age;

	protected Address address = new Address();

	protected ContactDetails contactDetails = new ContactDetails();

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return DateUtils.calcAge(dateOfBirth);
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

}
