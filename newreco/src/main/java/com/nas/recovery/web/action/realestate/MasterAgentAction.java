package com.nas.recovery.web.action.realestate;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.nas.recovery.domain.users.Role;
import com.nas.recovery.web.action.users.RoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("masterAgentAction")
public class MasterAgentAction extends MasterAgentActionBase implements
		java.io.Serializable {

	public static String DEF_ROLE = "realtor";

	@Override
	public String definedRole() {
		// TODO Auto-generated method stub
		return DEF_ROLE;
	}
	
	


}
