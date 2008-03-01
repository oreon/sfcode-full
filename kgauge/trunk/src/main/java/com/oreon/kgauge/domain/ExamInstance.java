package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class ExamInstance extends ExamInstanceBase {

	private static final Logger log = Logger.getLogger(ExamInstance.class);

	/* Default Constructor */
	public ExamInstance() {
	}

	public ExamInstance examInstanceInstance() {
		return this;
	}
}
