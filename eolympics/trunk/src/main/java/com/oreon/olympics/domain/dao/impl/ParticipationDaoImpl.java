package com.oreon.olympics.domain.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class ParticipationDaoImpl extends ParticipationDaoImplBase {

	private static final Logger log = Logger
			.getLogger(ParticipationDaoImpl.class);

	public ParticipationDaoImpl participationDaoImplInstance() {
		return this;
	}
}
