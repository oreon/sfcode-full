package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class ExamInstanceDaoImpl extends ExamInstanceDaoImplBase {

	private static final Logger log = Logger
			.getLogger(ExamInstanceDaoImpl.class);

	public ExamInstanceDaoImpl examInstanceDaoImplInstance() {
		return this;
	}
}
