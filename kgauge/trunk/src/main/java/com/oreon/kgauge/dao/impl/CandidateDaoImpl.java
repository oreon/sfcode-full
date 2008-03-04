package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class CandidateDaoImpl extends CandidateDaoImplBase {

	private static final Logger log = Logger.getLogger(CandidateDaoImpl.class);

	public CandidateDaoImpl candidateDaoImplInstance() {
		return this;
	}
}
