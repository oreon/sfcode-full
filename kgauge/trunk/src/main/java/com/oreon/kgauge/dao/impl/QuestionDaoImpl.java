package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class QuestionDaoImpl extends QuestionDaoImplBase {

	private static final Logger log = Logger.getLogger(QuestionDaoImpl.class);

	public QuestionDaoImpl questionDaoImplInstance() {
		return this;
	}
}
