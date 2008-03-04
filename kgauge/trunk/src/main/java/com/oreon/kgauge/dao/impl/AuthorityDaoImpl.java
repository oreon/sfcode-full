package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class AuthorityDaoImpl extends AuthorityDaoImplBase {

	private static final Logger log = Logger.getLogger(AuthorityDaoImpl.class);

	public AuthorityDaoImpl authorityDaoImplInstance() {
		return this;
	}
}
