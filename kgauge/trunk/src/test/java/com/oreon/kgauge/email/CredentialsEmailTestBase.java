package com.oreon.kgauge.email;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:/applicationContext.xml",
		"classpath:/emailContext.xml"})
public abstract class CredentialsEmailTestBase
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CredentialsEmail credentialsEmail;

	public CredentialsEmail getCredentialsEmail() {
		return credentialsEmail;
	}

	public void setCredentialsEmail(CredentialsEmail credentialsEmail) {
		this.credentialsEmail = credentialsEmail;
	}

	@Test
	public void testSendMail() {
		preSendMail();
		try {
			credentialsEmail.sendMessage();
		} catch (Throwable t) {
			fail("mail sending failed " + t.getMessage());
		}
		postSendMail();
	}

	public void preSendMail() {
	};
	public void postSendMail() {
	};
}
