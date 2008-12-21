package com.oreon.kgauge.email;

import org.apache.log4j.Logger;
import org.springframework.mail.MailMessage;

import com.oreon.kgauge.domain.Candidate;

public class CredentialsEmail extends CredentialsEmailBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(CredentialsEmail.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public CredentialsEmail() {
	}

	public CredentialsEmail credentialsEmailInstance() {
		return this;
	}

	@Override
	protected MailMessage createMessage() {
		mailMessage.setTo(getCandidate().getContactDetails().getEmail());
		return super.createMessage();
	}



}
