package com.oreon.kgauge.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.SectionService", serviceName = "SectionService")
public class SectionServiceImpl extends SectionServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(SectionServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
