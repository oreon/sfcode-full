package com.nas.recovery.web.action.legal;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.nas.recovery.domain.users.Role;
import com.nas.recovery.web.action.common.PersonAction;
import com.nas.recovery.web.action.users.RoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("lawyerAction")
public class LawyerAction extends LawyerActionBase implements
		java.io.Serializable {
	
	@In(create=true)
	RoleAction roleAction;
	
	@In(create=true)
	PersonAction personAction;
	
	
	public static final String ROLE_NAME = "lawyer";

	@Override
	public String save() {
		boolean isNew = getInstance().getId() == null;
		if (isNew) {
			Role role = createRole();
			getInstance().getUser().getRoles().add(role);
		}
		String result = super.save();
		if (isNew){
			personAction.setPerson(getInstance());
			sendMail("/mails/registrationSuccess.xhtml");
		}
		return result;
	}

	private Role createRole() {
		Role role = roleAction.findByName(ROLE_NAME);
		if(role == null) {
			role = new Role();
			role.setName(ROLE_NAME);
			roleAction.persist(role);
		}
		return role;
	}
	
}
