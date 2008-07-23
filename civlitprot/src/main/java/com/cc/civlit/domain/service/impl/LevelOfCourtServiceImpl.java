package com.cc.civlit.domain.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.cc.civlit.domain.service.LevelOfCourtService", serviceName = "LevelOfCourtService")
public class LevelOfCourtServiceImpl extends LevelOfCourtServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(LevelOfCourtServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
