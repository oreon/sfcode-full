package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class ExamCreator extends ExamCreatorBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(ExamCreator.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public ExamCreator() {
	}

	public ExamCreator examCreatorInstance() {
		return this;
	}

}
