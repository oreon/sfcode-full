package com.nas.recovery.web.action.legal;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.nas.recovery.domain.users.Role;
import com.nas.recovery.web.action.common.CurrentPersonAction;
import com.nas.recovery.web.action.users.RoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("lawyerAction")
public class LawyerAction extends LawyerActionBase implements
		java.io.Serializable {
	
	
	public static final String ROLE_NAME = "lawyer";


	@Override
	public String definedRole() {
		// TODO Auto-generated method stub
		return ROLE_NAME;
	}
	
}
