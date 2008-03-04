package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.AuthorityService", serviceName = "AuthorityService")
public class AuthorityServiceImpl extends AuthorityServiceImplBase {

	private static final Logger log = Logger
			.getLogger(AuthorityServiceImpl.class);

	public AuthorityServiceImpl authorityServiceImplInstance() {
		return this;
	}
}
