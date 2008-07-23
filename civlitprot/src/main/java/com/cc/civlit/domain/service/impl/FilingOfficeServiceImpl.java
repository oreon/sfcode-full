package com.cc.civlit.domain.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.cc.civlit.domain.service.FilingOfficeService", serviceName = "FilingOfficeService")
public class FilingOfficeServiceImpl extends FilingOfficeServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(FilingOfficeServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
