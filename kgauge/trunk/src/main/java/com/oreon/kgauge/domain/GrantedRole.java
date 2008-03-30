package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class GrantedRole extends GrantedRoleBase {

	private static final Logger log = Logger.getLogger(GrantedRole.class);

	/* Default Constructor */
	public GrantedRole() {
	}

	/* Constructor with all attributes */
	public GrantedRole(String name) {
		super(name);
	}

	public GrantedRole grantedRoleInstance() {
		return this;
	}

	
}
