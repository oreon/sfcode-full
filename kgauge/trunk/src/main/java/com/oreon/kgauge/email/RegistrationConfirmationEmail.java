package com.oreon.kgauge.email;

import org.apache.log4j.Logger;

public class RegistrationConfirmationEmail
		extends
			RegistrationConfirmationEmailBase implements java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(RegistrationConfirmationEmail.class);
	private static final long serialVersionUID = 1L;
	


	/* Default Constructor */
	public RegistrationConfirmationEmail() {
	}

	public RegistrationConfirmationEmail registrationConfirmationEmailInstance() {
		return this;
	}

}
