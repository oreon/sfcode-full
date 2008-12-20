package com.oreon.kgauge.service.impl;

import org.apache.log4j.Logger;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.service.CandidateService;
import com.oreon.kgauge.service.EmailService;
import com.oreon.kgauge.service.UserService;

public class EmailServiceImpl implements EmailService {

	private static final Logger log = Logger.getLogger(EmailServiceImpl.class);

	private MailSender mailSender;
	private SimpleMailMessage mailMessage;
	private CandidateService candidateService;
	private UserService userService;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	
	public void sendEmail(String fromAddress, String toAddress, String subject,
			String body) throws MailSendException {

	}

	public void emailPassword(String email) throws MailSendException {
		log.debug("Entering email password with mail sender" + this.mailSender
				+ " and " + "mail message " + this.mailMessage
				+ " with email id" + email);

		Candidate candidate = this.candidateService.findByEmail(email);

		String password = candidate.getUser().getPassword();

		this.mailMessage.setText("your password is stored as " + password);

		this.mailMessage.setTo(email);
		this.mailSender.send(this.mailMessage);

		log.debug("mail sent successfully to " + email);
	}

}
