package com.oreon.kgauge.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.AnswerChoiceService", serviceName = "AnswerChoiceService")
public class AnswerChoiceServiceImpl extends AnswerChoiceServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(AnswerChoiceServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
