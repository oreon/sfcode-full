package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class Participation extends ParticipationBase {

	private static final Logger log = Logger.getLogger(Participation.class);

	public Participation participationInstance() {
		return this;
	}
}
