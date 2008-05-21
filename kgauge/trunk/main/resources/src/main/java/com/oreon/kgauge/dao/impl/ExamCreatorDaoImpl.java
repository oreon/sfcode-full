package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class ExamCreatorDaoImpl extends ExamCreatorDaoImplBase {

	private static final Logger log = Logger
			.getLogger(ExamCreatorDaoImpl.class);

	public ExamCreatorDaoImpl examCreatorDaoImplInstance() {
		return this;
	}
}
