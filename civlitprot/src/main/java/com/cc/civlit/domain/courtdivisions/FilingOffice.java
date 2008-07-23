package com.cc.civlit.domain.courtdivisions;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class FilingOffice extends FilingOfficeBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(FilingOffice.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public FilingOffice() {
	}

	/* Constructor with all attributes */
	public FilingOffice(String name) {
		super(name);
	}

	public FilingOffice filingOfficeInstance() {
		return this;
	}

}
