package com.oreon.kgauge.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.ExamInstanceService", serviceName = "ExamInstanceService")
public class ExamInstanceServiceImpl extends ExamInstanceServiceImplBase {

	private static final Logger log = Logger
			.getLogger(ExamInstanceServiceImpl.class);

	public ExamInstanceServiceImpl examInstanceServiceImplInstance() {
		return this;
	}
}
