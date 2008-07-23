package com.cc.civlit.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class Firm extends FirmBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Firm.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Firm() {
	}

	/* Constructor with all attributes */
	public Firm(String firmName, FirmType firmType) {
		super(firmName, firmType);
	}

	public Firm firmInstance() {
		return this;
	}

}
