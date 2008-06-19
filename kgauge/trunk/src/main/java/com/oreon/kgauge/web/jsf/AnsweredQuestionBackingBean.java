package com.oreon.kgauge.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

import com.oreon.kgauge.service.AnsweredQuestionService;

import javax.jws.WebService;

public class AnsweredQuestionBackingBean
		extends
			AnsweredQuestionBackingBeanBase {

	private static final Logger log = Logger
			.getLogger(AnsweredQuestionBackingBean.class);

	public AnsweredQuestionService getAnsweredQuestionService() {
		return answeredQuestionService;
	}

}
