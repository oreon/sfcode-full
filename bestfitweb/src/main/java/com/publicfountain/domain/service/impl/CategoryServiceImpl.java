package com.publicfountain.domain.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CategoryServiceImpl extends CategoryServiceImplBase {

	private static final Logger log = Logger
			.getLogger(CategoryServiceImpl.class);

	public CategoryServiceImpl categoryServiceImplInstance() {
		return this;
	}
}
