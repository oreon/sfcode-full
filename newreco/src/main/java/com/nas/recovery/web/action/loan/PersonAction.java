
	
package com.nas.recovery.web.action.loan;


import org.jboss.seam.annotations.In;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

import com.nas.recovery.domain.loan.Person;
import com.nas.recovery.domain.users.Role;
import com.nas.recovery.web.action.common.CurrentPersonAction;
import com.nas.recovery.web.action.users.RoleAction;

public  abstract class PersonAction<T extends BusinessEntity> extends BaseAction<T> implements java.io.Serializable{
	
	@In(create=true)
	RoleAction roleAction;
	
	@In(create=true)
	CurrentPersonAction personAction;
	
	@Override
	public String save() {
		boolean isNew = getInstance().getId() == null;
		Person person = (Person)getInstance();
		if (isNew) {
			Role role = createRole();
			person.getUser().getRoles().add(role);
		}
		String result = super.save();
		if (isNew){
			personAction.setPerson(person);
			sendMail("/mails/registrationSuccess.xhtml");
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
	
	public abstract String definedRole();
	
}
	