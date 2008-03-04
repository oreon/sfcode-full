package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

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
