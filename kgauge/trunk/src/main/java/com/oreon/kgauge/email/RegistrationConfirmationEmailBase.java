
/**
 * This is generated code - to edit code or override methods use - RegistrationConfirmationEmail class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.email;

import org.springframework.mail.MailMessage;
import org.witchcraft.model.mail.AbstractMailer;

public abstract class RegistrationConfirmationEmailBase
		extends
			AbstractMailer {

	private com.oreon.kgauge.domain.dto.CandidateDto candidate;

	public com.oreon.kgauge.domain.dto.CandidateDto getCandidate() {
		return this.candidate;
	}

	public void setCandidate(com.oreon.kgauge.domain.dto.CandidateDto candidate) {
		this.candidate = candidate;
	}

	@Override
	protected MailMessage createMessage() {
		// TODO Auto-generated method stub
		return getMailMessage();
	}
}
