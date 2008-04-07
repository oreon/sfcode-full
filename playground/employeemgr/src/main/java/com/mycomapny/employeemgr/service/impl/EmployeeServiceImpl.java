package com.mycomapny.employeemgr.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.mycomapny.employeemgr.service.EmployeeService", serviceName = "EmployeeService")
public class EmployeeServiceImpl extends EmployeeServiceImplBase {

	private static final Logger log = Logger
			.getLogger(EmployeeServiceImpl.class);

	public EmployeeServiceImpl employeeServiceImplInstance() {
		return this;
	}
}
