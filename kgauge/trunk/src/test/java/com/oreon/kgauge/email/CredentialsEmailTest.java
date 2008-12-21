package com.oreon.kgauge.email;
import static org.junit.Assert.*;
import org.junit.Before;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.domain.User;

public class CredentialsEmailTest extends CredentialsEmailTestBase {
	Candidate candidate;
	
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

	
	
	public void preSendMail() {
		getCredentialsEmail().setCandidate(candidate);
	}

	public void postSendMail() {
		// assertEquals(credentialsEmail.getMailMessage().getSubject(),
		// "confirmation ");
	}
}
