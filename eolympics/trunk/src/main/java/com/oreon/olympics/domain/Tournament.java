package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class Tournament extends TournamentBase {

	private static final Logger log = Logger.getLogger(Tournament.class);

	public Tournament tournamentInstance() {
		return this;
	}
}
