package com.oreon.kgauge.domain;

import javax.persistence.MappedSuperclass;

import org.apache.log4j.Logger;

@MappedSuperclass
public class Person extends PersonBase {

	private static final Logger log = Logger.getLogger(Person.class);

	public Person personInstance() {
		return this;
	}
}
