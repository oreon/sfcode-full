package com.cc.civlit.domain.auth;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class Role extends RoleBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Role.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Role() {
	}

	/* Constructor with all attributes */
	public Role(String name) {
		super(name);
	}

	public Role roleInstance() {
		return this;
	}

}
