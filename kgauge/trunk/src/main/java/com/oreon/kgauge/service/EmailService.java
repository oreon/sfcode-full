package com.oreon.kgauge.service;

import org.springframework.mail.MailSendException;

public interface EmailService {

	void sendEmail(String fromAddress, String toAddress, String subject, String body) throws MailSendException;
	void emailPassword(String emailId); 
}
