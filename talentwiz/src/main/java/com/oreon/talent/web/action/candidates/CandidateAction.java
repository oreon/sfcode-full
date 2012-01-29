package com.oreon.talent.web.action.candidates;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Identity;
import org.witchcraft.seam.security.Authenticator;

//@Scope(ScopeType.CONVERSATION)
@Name("candidateAction")
public class CandidateAction extends CandidateActionBase implements
		java.io.Serializable {

	@In(create = true)
	Authenticator authenticator;
	
	@In
	Identity identity;

	public String register() {
		save();
		return SUCCESS;
	}

	public String editProfile() {
		setId(instance.getId());
		save();
		return SUCCESS;
	}

	@Override
	public String login() {
		if (authenticator.authenticate()){
			identity.login();
			return SUCCESS;
		}

		addErrorMessage("Login Failed");
		return FAILURE;
	}
}
