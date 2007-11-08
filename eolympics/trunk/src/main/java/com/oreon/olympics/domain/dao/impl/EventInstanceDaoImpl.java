package com.oreon.olympics.domain.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class EventInstanceDaoImpl extends EventInstanceDaoImplBase {

	private static final Logger log = Logger
			.getLogger(EventInstanceDaoImpl.class);

	public EventInstanceDaoImpl eventInstanceDaoImplInstance() {
		return this;
	}
}
