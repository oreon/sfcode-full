package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.QuestionService", serviceName = "QuestionService")
public class QuestionServiceImpl extends QuestionServiceImplBase {

	private static final Logger log = Logger
			.getLogger(QuestionServiceImpl.class);

	public QuestionServiceImpl questionServiceImplInstance() {
		return this;
	}
}
