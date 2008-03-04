package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class AnswerChoice extends AnswerChoiceBase {

	private static final Logger log = Logger.getLogger(AnswerChoice.class);

	/* Default Constructor */
	public AnswerChoice() {
	}

	/* Constructor with all attributes */
	public AnswerChoice(String answerText, Integer score) {
		super(answerText, score);
	}

	public AnswerChoice answerChoiceInstance() {
		return this;
	}
}
