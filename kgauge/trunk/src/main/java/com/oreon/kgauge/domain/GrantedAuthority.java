package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class GrantedAuthority extends GrantedAuthorityBase {

	private static final Logger log = Logger.getLogger(GrantedAuthority.class);

	/* Default Constructor */
	public GrantedAuthority() {
	}

	/* Constructor with all attributes */
	public GrantedAuthority(String name) {
		super(name);
	}

	public GrantedAuthority grantedAuthorityInstance() {
		return this;
	}

	public String getAuthority() {
		return getName();
	}

}
