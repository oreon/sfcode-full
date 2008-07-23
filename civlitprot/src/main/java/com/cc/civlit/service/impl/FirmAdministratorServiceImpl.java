package com.cc.civlit.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.cc.civlit.service.FirmAdministratorService", serviceName = "FirmAdministratorService")
public class FirmAdministratorServiceImpl
		extends
			FirmAdministratorServiceImplBase implements java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(FirmAdministratorServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
