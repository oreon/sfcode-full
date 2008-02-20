package com.oreon.kgauge.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.CategoryService", serviceName = "CategoryService")
public class CategoryServiceImpl extends CategoryServiceImplBase {

	private static final Logger log = Logger
			.getLogger(CategoryServiceImpl.class);

	public CategoryServiceImpl categoryServiceImplInstance() {
		return this;
	}
}
