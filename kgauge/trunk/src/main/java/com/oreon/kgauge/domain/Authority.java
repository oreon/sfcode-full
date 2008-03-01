package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class Authority extends AuthorityBase {

	private static final Logger log = Logger.getLogger(Authority.class);

	/* Default Constructor */
	public Authority() {
	}

	/* Constructor with all attributes */
	public Authority(String name) {
		super(name);
	}

	public Authority authorityInstance() {
		return this;
	}
}
