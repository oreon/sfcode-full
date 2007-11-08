package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class TeamEventInstance extends TeamEventInstanceBase {

	private static final Logger log = Logger.getLogger(TeamEventInstance.class);

	public TeamEventInstance teamEventInstanceInstance() {
		return this;
	}
}
