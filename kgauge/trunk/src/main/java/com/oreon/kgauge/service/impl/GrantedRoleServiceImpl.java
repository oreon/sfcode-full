package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.GrantedRoleService", serviceName = "GrantedRoleService")
public class GrantedRoleServiceImpl extends GrantedRoleServiceImplBase {

	private static final Logger log = Logger
			.getLogger(GrantedRoleServiceImpl.class);

}
