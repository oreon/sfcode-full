package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.CategoryService", serviceName = "CategoryService")
public class CategoryServiceImpl extends CategoryServiceImplBase {

	private static final Logger log = Logger
			.getLogger(CategoryServiceImpl.class);

	public CategoryServiceImpl categoryServiceImplInstance() {
		return this;
	}
}
