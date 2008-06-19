package com.oreon.kgauge.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.kgauge.service.ExamInstanceService", serviceName = "ExamInstanceService")
public class ExamInstanceServiceImpl extends ExamInstanceServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(ExamInstanceServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
