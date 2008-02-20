package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class ExamCreator extends ExamCreatorBase {

	private static final Logger log = Logger.getLogger(ExamCreator.class);

	public ExamCreator examCreatorInstance() {
		return this;
	}
}
