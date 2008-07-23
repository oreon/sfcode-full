package com.cc.civlit.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.cc.civlit.service.FirmService", serviceName = "FirmService")
public class FirmServiceImpl extends FirmServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(FirmServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
