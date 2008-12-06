package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class ExamDaoImpl extends ExamDaoImplBase {

	private static final Logger log = Logger.getLogger(ExamDaoImpl.class);

	public ExamDaoImpl examDaoImplInstance() {
		return this;
	}
	
	
}
