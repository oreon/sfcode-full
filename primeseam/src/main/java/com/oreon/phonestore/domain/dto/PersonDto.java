package com.oreon.phonestore.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class PersonDto extends BaseEntity {

	protected String firstName;

	protected String lastName;

	protected ContactDetailsDto contactDetailsDto;

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

	public void setContactDetails(ContactDetailsDto contactDetailsDto) {
		this.contactDetailsDto = contactDetailsDto;
	}

	public ContactDetailsDto getContactDetails() {
		return contactDetailsDto;
	}

}
