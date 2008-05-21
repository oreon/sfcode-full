package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@MappedSuperclass
public class Person extends PersonBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Person.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Person() {
	}

	/* Constructor with all attributes */
	public Person(String firstName, String lastName, Date dateOfBirth) {
		super(firstName, lastName, dateOfBirth);
	}

	public Person personInstance() {
		return this;
	}

}
