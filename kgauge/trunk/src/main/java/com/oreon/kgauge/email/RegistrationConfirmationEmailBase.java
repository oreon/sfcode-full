
/**
 * This is generated code - to edit code or override methods use - RegistrationConfirmationEmail class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.MailMessage;
import org.witchcraft.model.mail.AbstractMailer;

public abstract class RegistrationConfirmationEmailBase extends AbstractMailer {

	private com.oreon.kgauge.domain.Candidate candidate;

	public com.oreon.kgauge.domain.Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(com.oreon.kgauge.domain.Candidate candidate) {
		this.candidate = candidate;
	}

	private String templateName = "/mailTemplates/registrationConfirmationEmail.vm";

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
	protected MailMessage createMessage() {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("candidate", candidate);

		String result = createMessageBody(model);
		mailMessage.setText(result);
		return mailMessage;
	}
}
