package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Identity;
import org.witchcraft.base.entity.UserUtilAction;

import com.oreon.cerebrum.employee.Employee;
import com.oreon.cerebrum.employee.Physician;
import com.oreon.cerebrum.users.AppRole;
import com.oreon.cerebrum.web.action.users.AppRoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("physicianAction")
public class PhysicianAction extends PhysicianActionBase implements
		java.io.Serializable {

	@In(create = true)
	AppRoleAction appRoleAction;
	
	@In(create = true)
	UserUtilAction userUtilAction;

	@Override
	public String save() {
		if (isNew()) {
			addRole();
		}
		return super.save();
	}

	public void addRole() {
		AppRole role = appRoleAction.findByUnqName(DEFAULT_ROLE_NAME);
		getInstance().getAppUser().addAppRole(role);
	}

	@Override
	//add current user's facility to the newly created employee
	protected Physician createInstance() {
		Physician result = super.createInstance();
		result.setFacility(userUtilAction.getCurrentFacility());
		return result;
	}

	
	public Employee getCurrentLoggedInEmployee() {
		String query = "Select e from Employee e where e.appUser.userName = ?1";
		return  executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}
}
