package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class User extends UserBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(User.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public User() {
	}

	/* Constructor with all attributes */
	public User(String username, String password, boolean enabled) {
		super(username, password, enabled);
	}

	public User userInstance() {
		return this;
	}

}
