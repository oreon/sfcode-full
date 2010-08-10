package com.nas.recovery.web.action.users;

import org.jboss.seam.annotations.Name;

//@Scope(ScopeType.CONVERSATION)
@Name("roleAction")
public class RoleAction extends RoleActionBase implements java.io.Serializable {

	
	public org.wc.trackrite.users.Role findByName(String name) {

		return executeSingleResultNamedQuery("findByName", name);

	}
}
