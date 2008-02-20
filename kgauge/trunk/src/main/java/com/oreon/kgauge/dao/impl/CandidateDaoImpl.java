package com.oreon.kgauge.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@org.springframework.stereotype.Repository
public class CandidateDaoImpl extends CandidateDaoImplBase {

	private static final Logger log = Logger.getLogger(CandidateDaoImpl.class);

	public CandidateDaoImpl candidateDaoImplInstance() {
		return this;
	}
}
