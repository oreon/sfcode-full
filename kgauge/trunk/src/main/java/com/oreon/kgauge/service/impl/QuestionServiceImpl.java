package com.oreon.kgauge.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.QuestionService", serviceName = "QuestionService")
public class QuestionServiceImpl extends QuestionServiceImplBase {

	private static final Logger log = Logger
			.getLogger(QuestionServiceImpl.class);

	public QuestionServiceImpl questionServiceImplInstance() {
		return this;
	}
}
