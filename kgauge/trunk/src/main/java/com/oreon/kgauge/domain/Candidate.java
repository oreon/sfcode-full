package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

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
