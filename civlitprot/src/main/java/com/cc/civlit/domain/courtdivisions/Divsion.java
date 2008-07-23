package com.cc.civlit.domain.courtdivisions;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class Divsion extends DivsionBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Divsion.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Divsion() {
	}

	/* Constructor with all attributes */
	public Divsion(String name) {
		super(name);
	}

	public Divsion divsionInstance() {
		return this;
	}

}
