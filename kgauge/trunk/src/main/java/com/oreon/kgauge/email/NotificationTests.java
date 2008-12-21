package com.oreon.kgauge.email;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.domain.User;

@ContextConfiguration(locations = { "classpath:/applicationContext.xml",
		"classpath:/emailContext.xml" })
public class NotificationTests extends
		AbstractTransactionalJUnit4SpringContextTests {

	Candidate candidate;

	@Autowired
	CredentialsEmail credentialsEmail;

	@Before
	public void createCandidate() {
		candidate = new Candidate();
		candidate.setFirstName("jess");
		User user = new User();
		user.setPassword("iceland");
		user.setUsername("jsingh");
		candidate.setUser(user);
		candidate.getContactDetails().setEmail("singhjess@gmail.com");
	}

	public CredentialsEmail getCredentialsEmail() {
		return credentialsEmail;
	}

	public void setCredentialsEmail(CredentialsEmail credentialsEmail) {
		this.credentialsEmail = credentialsEmail;
	}

	private void assertEverything() {
		// assertTrue("The email service is empty", notificationService !=
		// null);
		// assertTrue("The email parameteres not available",
		// emailParameters != null);
		// assertTrue("The document does not exist", doc != null);
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
		credentialsEmail.setCandidate(candidate);
	}

	public void postSendMail() {
		// assertEquals(credentialsEmail.getMailMessage().getSubject(),
		// "confirmation ");
	}

}