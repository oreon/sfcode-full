package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class CategoryDaoImpl extends CategoryDaoImplBase {

	private static final Logger log = Logger.getLogger(CategoryDaoImpl.class);

	public CategoryDaoImpl categoryDaoImplInstance() {
		return this;
	}
}
