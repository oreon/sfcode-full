package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

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
