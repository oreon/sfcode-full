package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class AnswerChoice extends AnswerChoiceBase {

	private static final Logger log = Logger.getLogger(AnswerChoice.class);

	public AnswerChoice answerChoiceInstance() {
		return this;
	}
}
