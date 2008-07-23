package com.cc.civlit.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Embeddable
public class ContactDetails extends ContactDetailsBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(ContactDetails.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public ContactDetails() {
	}

	/* Constructor with all attributes */
	public ContactDetails(String address1, String address2, String city,
			String state, String country, String postalCode, String phone,
			String fax, String email) {
		super(address1, address2, city, state, country, postalCode, phone, fax,
				email);
	}

	public ContactDetails contactDetailsInstance() {
		return this;
	}

}
