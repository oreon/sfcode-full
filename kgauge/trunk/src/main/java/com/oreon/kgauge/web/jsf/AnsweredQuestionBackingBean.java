package com.oreon.kgauge.web.jsf;

import org.apache.log4j.Logger;

import com.oreon.kgauge.service.AnsweredQuestionService;

public class AnsweredQuestionBackingBean
		extends
			AnsweredQuestionBackingBeanBase {

	private static final Logger log = Logger
			.getLogger(AnsweredQuestionBackingBean.class);

	public AnsweredQuestionService getAnsweredQuestionService() {
		return answeredQuestionService;
	}

}
