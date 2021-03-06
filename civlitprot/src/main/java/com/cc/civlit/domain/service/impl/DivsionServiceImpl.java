package com.cc.civlit.domain.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.cc.civlit.domain.service.DivsionService", serviceName = "DivsionService")
public class DivsionServiceImpl extends DivsionServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(DivsionServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
