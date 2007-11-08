package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class TeamEvent extends TeamEventBase {

	private static final Logger log = Logger.getLogger(TeamEvent.class);

	public TeamEvent teamEventInstance() {
		return this;
	}
}
