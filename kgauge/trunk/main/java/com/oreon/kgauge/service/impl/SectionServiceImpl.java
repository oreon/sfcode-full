package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.SectionService", serviceName = "SectionService")
public class SectionServiceImpl extends SectionServiceImplBase {

	private static final Logger log = Logger
			.getLogger(SectionServiceImpl.class);

	public SectionServiceImpl sectionServiceImplInstance() {
		return this;
	}
}
