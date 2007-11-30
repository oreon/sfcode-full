package com.oreon.jshoppingcart.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class ProductDaoImpl extends ProductDaoImplBase {

	private static final Logger log = Logger.getLogger(ProductDaoImpl.class);

	public ProductDaoImpl productDaoImplInstance() {
		return this;
	}
}
