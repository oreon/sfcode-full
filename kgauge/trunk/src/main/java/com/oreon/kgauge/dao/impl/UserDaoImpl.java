package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class UserDaoImpl extends UserDaoImplBase {

	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl userDaoImplInstance() {
		return this;
	}
}
