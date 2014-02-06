
	
package com.oreon.cerebrum.web.action.employee;
	

import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Identity;

import com.oreon.cerebrum.employee.Physician;

	
//@Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase implements java.io.Serializable{
	
	public Physician getCurrentLoggedInEmployee() {
		String query = "Select e from Employee e where e.appUser.userName = ?1";
		return (Physician) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}
	
}
	