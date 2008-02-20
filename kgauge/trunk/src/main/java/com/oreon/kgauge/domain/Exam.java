package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class Exam extends ExamBase {

	private static final Logger log = Logger.getLogger(Exam.class);

	public Exam examInstance() {
		return this;
	}
}
