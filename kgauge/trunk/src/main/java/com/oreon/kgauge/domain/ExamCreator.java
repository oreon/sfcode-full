package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class ExamCreator extends ExamCreatorBase {

	private static final Logger log = Logger.getLogger(ExamCreator.class);

	/* Default Constructor */
	public ExamCreator() {
	}

	public ExamCreator examCreatorInstance() {
		return this;
	}
}
