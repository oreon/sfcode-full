package com.cc.civlit.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.cc.civlit.service.CaseAdministratorService", serviceName = "CaseAdministratorService")
public class CaseAdministratorServiceImpl
		extends
			CaseAdministratorServiceImplBase implements java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(CaseAdministratorServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
