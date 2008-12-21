package com.oreon.kgauge.email;
import static org.junit.Assert.*;
import org.junit.Before;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.domain.User;

public class RegistrationConfirmationEmailTest
		extends
			RegistrationConfirmationEmailTestBase {
	
	Candidate candidate = new Candidate();

	//FIXME: This should come from an object mother
	public Candidate createCandidate() {
		
		candidate.setFirstName("jess");
		User user = new User();
		user.setPassword("iceland");
		user.setUsername("jsingh");
		candidate.setUser(user);
		candidate.getContactDetails().setEmail("singhjess@gmail.com");
		return candidate;
	}

	
	
	public void preSendMail() {
		getRegistrationConfirmationEmail().setCandidate(createCandidate());
	}

	
}
