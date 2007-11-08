package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class EventInstance extends EventInstanceBase {

	private static final Logger log = Logger.getLogger(EventInstance.class);

	public EventInstance eventInstanceInstance() {
		return this;
	}
}
