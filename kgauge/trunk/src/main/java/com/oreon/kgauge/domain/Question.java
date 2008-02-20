package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class Question extends QuestionBase {

	private static final Logger log = Logger.getLogger(Question.class);

	public Question questionInstance() {
		return this;
	}
}
