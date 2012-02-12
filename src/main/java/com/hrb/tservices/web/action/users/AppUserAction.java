
	
package com.hrb.tservices.web.action.users;
	

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.management.IdentityManager;

	
//@Scope(ScopeType.CONVERSATION)

@Name("appUserAction")
public class AppUserAction extends AppUserActionBase implements java.io.Serializable{

	@In(create=true)
	IdentityManager identityManager;
	
	@Override
	public String save() {	
		String ret  = super.save();
		identityManager.changePassword(instance.getUserName(), instance.getPassword());
		//identityManager.createUser(instance.getUserName(), instance.getPassword());
		return "success";
	}
}
	