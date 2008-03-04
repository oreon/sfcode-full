package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class Question extends QuestionBase {

	private static final Logger log = Logger.getLogger(Question.class);

	public Question questionInstance() {
		return this;
	}
}
