package com.oreon.kgauge.email;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:/applicationContext.xml",
		"classpath:/emailContext.xml"})
public abstract class RegistrationConfirmationEmailTestBase
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private RegistrationConfirmationEmail registrationConfirmationEmail;

	public RegistrationConfirmationEmail getRegistrationConfirmationEmail() {
		return registrationConfirmationEmail;
	}

	public void setRegistrationConfirmationEmail(
			RegistrationConfirmationEmail registrationConfirmationEmail) {
		this.registrationConfirmationEmail = registrationConfirmationEmail;
	}

	@Test
	public void testSendMail() {
		preSendMail();
		try {
			registrationConfirmationEmail.sendMessage();
		} catch (Throwable t) {
			t.printStackTrace();
			fail("mail sending failed " + t.getMessage());
			return;
		}
		postSendMail();
	}

	public void preSendMail() {
	};
	public void postSendMail() {
	};
}
