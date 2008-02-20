package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class User extends UserBase {

	private static final Logger log = Logger.getLogger(User.class);

	public User userInstance() {
		return this;
	}
}
