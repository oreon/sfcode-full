package com.nas.recovery.web.action.users;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.witchcraft.seam.security.Authenticator;

import com.nas.recovery.domain.users.User;

//@Scope(ScopeType.CONVERSATION)
@Name("userAction")
public class UserAction extends UserActionBase implements java.io.Serializable {
	
	@In(create=true)
	Authenticator authenticator;

	public String retrieveCredentials() {
		
		String email = getInstance().getEmail();

		User user = findUserByEmail(email);
		if (user == null) {
			statusMessages.addFromResourceBundle("noSuchCustomer", email);
			return "failure";
		}
		setInstance(user);
		
		sendMail("/mails/retrievalEmail.xhtml");
		statusMessages.addFromResourceBundle("credentialsEmailed", email);
		
		return "success";

	}
	
	public String login(){
		if( authenticator.authenticate() ) 
			return "success";
		return "failure";
	}

}
