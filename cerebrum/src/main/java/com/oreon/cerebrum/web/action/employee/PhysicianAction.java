package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

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
	protected Physician createInstance() {
		Physician result = super.createInstance();
		result.setFacility(getCurrentLoggedInPhysician().getFacility());
		//addFacility();
		return result;
	}

	public void addFacility() {

		

	}
}
