package com.oreon.jshoppingcart.businessservice.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ShoppingCartServiceImpl extends ShoppingCartServiceImplBase {

	private static final Logger log = Logger
			.getLogger(ShoppingCartServiceImpl.class);

	public ShoppingCartServiceImpl shoppingCartServiceImplInstance() {
		return this;
	}
}
