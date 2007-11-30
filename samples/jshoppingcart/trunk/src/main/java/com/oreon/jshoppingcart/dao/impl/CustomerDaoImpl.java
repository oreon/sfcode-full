package com.oreon.jshoppingcart.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class CustomerDaoImpl extends CustomerDaoImplBase {

	private static final Logger log = Logger.getLogger(CustomerDaoImpl.class);

	public CustomerDaoImpl customerDaoImplInstance() {
		return this;
	}
}
