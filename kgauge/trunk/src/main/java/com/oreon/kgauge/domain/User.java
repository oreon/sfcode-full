package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class User extends UserBase {

	private static final Logger log = Logger.getLogger(User.class);

	/* Default Constructor */
	public User() {
	}

	/* Constructor with all attributes */
	public User(String userName, String password, Boolean enabled) {
		super(userName, password, enabled);
	}

	public User userInstance() {
		return this;
	}
}
