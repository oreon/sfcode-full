package com.oreon.kgauge.domain;

import javax.persistence.Embeddable;

import org.apache.log4j.Logger;

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
	public ContactDetails(String streetAddress, String city, String state,
			String country, String zip, String phone, String email) {
		super(streetAddress, city, state, country, zip, phone, email);
	}

	public ContactDetails contactDetailsInstance() {
		return this;
	}

}
