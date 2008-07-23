package com.cc.civlit.domain.courtdivisions;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class LevelOfCourt extends LevelOfCourtBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(LevelOfCourt.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public LevelOfCourt() {
	}

	/* Constructor with all attributes */
	public LevelOfCourt(String name) {
		super(name);
	}

	public LevelOfCourt levelOfCourtInstance() {
		return this;
	}

}
