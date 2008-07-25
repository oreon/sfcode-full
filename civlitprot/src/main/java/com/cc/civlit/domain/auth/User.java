package com.cc.civlit.domain.auth;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class User extends UserBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(User.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public User() {
	}

	/* Constructor with all attributes */
	public User(String userName, String password, boolean enabled,
			String username) {
		super();
	}

	public User userInstance() {
		return this;
	}

}
