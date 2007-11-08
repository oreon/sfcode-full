package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class Team extends TeamBase {

	private static final Logger log = Logger.getLogger(Team.class);

	public Team teamInstance() {
		return this;
	}
}
