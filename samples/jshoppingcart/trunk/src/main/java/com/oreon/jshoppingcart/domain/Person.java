package com.oreon.jshoppingcart.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@MappedSuperclass
public class Person extends PersonBase {

	private static final Logger log = Logger.getLogger(Person.class);

	public Person personInstance() {
		return this;
	}
}
