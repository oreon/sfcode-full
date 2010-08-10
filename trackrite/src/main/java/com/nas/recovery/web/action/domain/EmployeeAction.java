package com.nas.recovery.web.action.domain;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.wc.trackrite.users.Role;

import com.nas.recovery.web.action.users.RoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase implements
		java.io.Serializable {
	
	@In(create=true)
	RoleAction roleAction;
	
	@Override
	public String save() {
		boolean isNew = getInstance().getId() == null;
		if (isNew) {
			Role role = createRole();
			instance.getUser().getRoles().add(role);
		}
		String result = super.save();
		if (isNew){
			//personAction.setPerson(person);
			//sendMail("/mails/registrationSuccess.xhtml");
		}
		return result;
	}

	private Role createRole() {
		Role role = roleAction.findByName(definedRole());
		if(role == null) {
			role = new Role();
			role.setName(definedRole());
			roleAction.persist(role);
		}
		return role;
	}

	private String definedRole() {
		// TODO Auto-generated method stub
		return "developer";
	}

}
