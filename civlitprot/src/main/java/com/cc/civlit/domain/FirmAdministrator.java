package com.cc.civlit.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class FirmAdministrator extends FirmAdministratorBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(FirmAdministrator.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public FirmAdministrator() {
	}

	/* Constructor with all attributes */
	public FirmAdministrator(String email) {
		super(email);
	}

	public FirmAdministrator firmAdministratorInstance() {
		return this;
	}

}
