package com.cc.civlit.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@MappedSuperclass
public class Party extends PartyBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Party.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Party() {
	}

	public Party partyInstance() {
		return this;
	}

}
