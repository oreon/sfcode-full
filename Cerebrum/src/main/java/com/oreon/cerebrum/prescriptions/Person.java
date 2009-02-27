package com.oreon.cerebrum.prescriptions;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.Date;

@MappedSuperclass
public class Person extends PersonBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Person.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Person() {
	}

	/* Constructor with all attributes 
	public Person(String firstName, String lastName, Date dateOfBirth,
			Gender gender) {
		super(firstName, lastName, dateOfBirth, gender);
	}*/

	public Person personInstance() {
		return this;
	}

}
