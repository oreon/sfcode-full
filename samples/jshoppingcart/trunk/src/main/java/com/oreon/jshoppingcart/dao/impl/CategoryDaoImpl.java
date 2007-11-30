package com.oreon.jshoppingcart.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class CategoryDaoImpl extends CategoryDaoImplBase {

	private static final Logger log = Logger.getLogger(CategoryDaoImpl.class);

	public CategoryDaoImpl categoryDaoImplInstance() {
		return this;
	}
	
	
}
