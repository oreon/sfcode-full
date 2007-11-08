package com.oreon.olympics.domain.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class EventInstanceServiceImpl extends EventInstanceServiceImplBase {

	private static final Logger log = Logger
			.getLogger(EventInstanceServiceImpl.class);

	public EventInstanceServiceImpl eventInstanceServiceImplInstance() {
		return this;
	}
}
