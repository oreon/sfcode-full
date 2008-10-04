package com.oreon.kgauge.web.jsf;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.witchcraft.model.data.location.Country;
import org.witchcraft.model.data.location.State;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.service.EmailService;

public class CandidateBackingBean extends CandidateBackingBeanBase {

	private static final Logger log = Logger
			.getLogger(CandidateBackingBean.class);

	private static List<State> listStates = new ArrayList<State>();
	
	private EmailService emailService;
	
	
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public String emailPassword() {
		String email = candidate.getContactDetails().getEmail();
		Candidate candidate = candidateService.findByEmail(email);
		if (candidate == null) {
			log.info("No candidate found with email " + email);

			//String message = JsfFunctions.getMessageFromBundle("no_such_email",
			//		new String[] { email });
			createErrorMessage("no_such_email",  email );
			return "failure";
		} else {  
			emailService.emailPassword(email);
			log.info(" candidate found with email " + email
					+ ", emailing password");
//			String message = JsfFunctions.getMessageFromBundle(
//					"password_mailed", new String[] { email });
			createSuccessMessage("password_mailed", email);
			return "emailFound";
		}
	}

	public List autoCompleteState(Object enteredText){
		List<State> results = new ArrayList();
		log.debug("autocomplete state called for " + enteredText);
		
		for (State state : getStatesList()) {
			String stateName = state.getName().toUpperCase();
			String text = ((String)enteredText).toUpperCase();
			if(stateName.startsWith(text)){
				results.add(state);
			}
		}
		
		return results; 
	}

	private static List<State> getStatesList() {
		if (listStates.isEmpty()) {
			Country us = new Country("USA", "US", 1);
			Country ca = new Country("Canada", "CA", 2);

			listStates.add(new State(us, "Ohio"));
			listStates.add(new State(us, "California"));
			listStates.add(new State(us, "Kansas"));
			listStates.add(new State(us, "Philadelphia"));

			listStates.add(new State(ca, "Ontario"));
			listStates.add(new State(ca, "PEI"));
		}
		return listStates;
	}

}
