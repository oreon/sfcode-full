package com.nas.recovery.web.action.realestate;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.nas.recovery.domain.users.Role;
import com.nas.recovery.web.action.users.RoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("masterAgentAction")
public class MasterAgentAction extends MasterAgentActionBase implements
		java.io.Serializable {

	@In(create=true)
	RoleAction roleAction;
	
	@Override
	public String save() {
		boolean isNew = getInstance().getId() == null;
		if (isNew) {
			Role role = roleAction.loadFromId(6L);
			getInstance().getUser().getRoles().add(role);
		}
		String result = super.save();
		if (isNew){
			sendMail("/mails/registrationSuccess.xhtml");
		}
		return result;
	}
	
	
	public String sendRegMail(){
		return "a" ;
		//sendMail("/mails/registrationSuccess.xhtml");
	}

}
