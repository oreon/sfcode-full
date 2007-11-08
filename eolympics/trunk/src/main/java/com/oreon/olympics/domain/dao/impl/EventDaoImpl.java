package com.oreon.olympics.domain.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class EventDaoImpl extends EventDaoImplBase {

	private static final Logger log = Logger.getLogger(EventDaoImpl.class);

	public EventDaoImpl eventDaoImplInstance() {
		return this;
	}
}
