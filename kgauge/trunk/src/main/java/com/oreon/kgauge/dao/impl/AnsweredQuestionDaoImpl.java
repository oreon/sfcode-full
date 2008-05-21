package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class AnsweredQuestionDaoImpl extends AnsweredQuestionDaoImplBase {

	private static final Logger log = Logger
			.getLogger(AnsweredQuestionDaoImpl.class);

	public AnsweredQuestionDaoImpl answeredQuestionDaoImplInstance() {
		return this;
	}
}
