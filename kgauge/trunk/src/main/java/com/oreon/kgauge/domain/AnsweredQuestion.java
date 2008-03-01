package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class AnsweredQuestion extends AnsweredQuestionBase {

	private static final Logger log = Logger.getLogger(AnsweredQuestion.class);

	/* Default Constructor */
	public AnsweredQuestion() {
	}

	public AnsweredQuestion answeredQuestionInstance() {
		return this;
	}
}
