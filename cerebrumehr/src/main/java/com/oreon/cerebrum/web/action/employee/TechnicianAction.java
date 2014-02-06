
	
package com.oreon.cerebrum.web.action.employee;
	

import org.jboss.seam.annotations.Name;

	
//@Scope(ScopeType.CONVERSATION)
@Name("technicianAction")
public class TechnicianAction extends TechnicianActionBase implements java.io.Serializable{

	@Override
	public String getDefaultRoleName() {
		// TODO fix this should be default role name
		return "TECH";
	}
	
}
	