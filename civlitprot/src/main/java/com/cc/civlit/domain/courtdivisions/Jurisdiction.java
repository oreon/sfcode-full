package com.cc.civlit.domain.courtdivisions;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class Jurisdiction extends JurisdictionBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(Jurisdiction.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Jurisdiction() {
	}

	/* Constructor with all attributes */
	public Jurisdiction(String name) {
		super(name);
	}

	public Jurisdiction jurisdictionInstance() {
		return this;
	}

}
