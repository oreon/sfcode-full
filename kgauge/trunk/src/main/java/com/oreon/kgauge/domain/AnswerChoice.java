package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class AnswerChoice extends AnswerChoiceBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(AnswerChoice.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public AnswerChoice() {
	}

	/* Constructor with all attributes */
	public AnswerChoice(String answerText, Integer score, boolean correctAnswer) {
		super(answerText, score, correctAnswer);
	}

	public AnswerChoice answerChoiceInstance() {
		return this;
	}

}
