package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.AnswerChoiceService", serviceName = "AnswerChoiceService")
public class AnswerChoiceServiceImpl extends AnswerChoiceServiceImplBase {

	private static final Logger log = Logger
			.getLogger(AnswerChoiceServiceImpl.class);

	public AnswerChoiceServiceImpl answerChoiceServiceImplInstance() {
		return this;
	}
}
