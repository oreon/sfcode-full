package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Embeddable
public class ContactDetails extends ContactDetailsBase {

	private static final Logger log = Logger.getLogger(ContactDetails.class);

	public ContactDetails contactDetailsInstance() {
		return this;
	}
}
