package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class GrantedRole extends GrantedRoleBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(GrantedRole.class);
	private static final long serialVersionUID = 1L;

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
