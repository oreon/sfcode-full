package com.oreon.jshoppingcart.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class EmployeeServiceImpl extends EmployeeServiceImplBase {

	private static final Logger log = Logger
			.getLogger(EmployeeServiceImpl.class);

	public EmployeeServiceImpl employeeServiceImplInstance() {
		return this;
	}
}
