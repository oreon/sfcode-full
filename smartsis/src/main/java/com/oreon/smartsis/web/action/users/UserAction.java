package com.oreon.smartsis.web.action.users;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.witchcraft.seam.security.Authenticator;

import com.oreon.smartsis.users.User;

//@Scope(ScopeType.CONVERSATION)
@Name("userAction")
public class UserAction extends UserActionBase implements java.io.Serializable {

	@In(create = true)
	Authenticator authenticator;

	public String retrieveCredentials() {

		String email = getInstance().getEmail();

		User user = findByUnqEmail(email);
		if (user == null) {
			statusMessages.addFromResourceBundle("noSuchCustomer", email);
			return "failure";
		}
		setInstance(user);

		sendMail("/mails/retrievalEmail.xhtml");
		statusMessages.addFromResourceBundle("credentialsEmailed", email);

		return "success";

	}

	public String login() {
		if (authenticator.authenticate())
			return "success";
		addErrorMessage("Invalid username/password ");
		return "failure";
	}

}
