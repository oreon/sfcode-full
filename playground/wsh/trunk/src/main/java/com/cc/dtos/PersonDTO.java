package com.cc.dtos;

import org.apache.log4j.Logger;

import com.cc.dto.BaseDTO;

public class PersonDTO extends BaseDTO {
	private static final Logger logger = Logger.getLogger(PersonDTO.class);
	private static final String TABLE_NAME= "cc_poc";
	
	private String firstName;
	
	private String lastName;

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
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
}
