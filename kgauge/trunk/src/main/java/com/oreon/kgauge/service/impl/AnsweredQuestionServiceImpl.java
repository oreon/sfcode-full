package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.AnsweredQuestionService", serviceName = "AnsweredQuestionService")
public class AnsweredQuestionServiceImpl
		extends
			AnsweredQuestionServiceImplBase implements java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(AnsweredQuestionServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
