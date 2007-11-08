package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class Event extends EventBase {

	private static final Logger log = Logger.getLogger(Event.class);

	public Event eventInstance() {
		return this;
	}
}
