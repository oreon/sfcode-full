package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.CandidateService", serviceName = "CandidateService")
public class CandidateServiceImpl extends CandidateServiceImplBase {

	private static final Logger log = Logger
			.getLogger(CandidateServiceImpl.class);

	public CandidateServiceImpl candidateServiceImplInstance() {
		return this;
	}
}
