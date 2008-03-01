package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class Candidate extends CandidateBase {

	private static final Logger log = Logger.getLogger(Candidate.class);

	/* Default Constructor */
	public Candidate() {
	}

	public Candidate candidateInstance() {
		return this;
	}
}
