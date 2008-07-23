package com.cc.civlit.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class CaseAdministrator extends CaseAdministratorBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(CaseAdministrator.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public CaseAdministrator() {
	}

	/* Constructor with all attributes */
	public CaseAdministrator(String email) {
		super(email);
	}

	public CaseAdministrator caseAdministratorInstance() {
		return this;
	}

}
