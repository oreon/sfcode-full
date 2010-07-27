package com.nas.recovery.web.action.propertymanagement;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import com.nas.recovery.domain.users.Role;
import com.nas.recovery.web.action.common.PersonAction;
import com.nas.recovery.web.action.users.RoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("propertyManagerAction")
public class PropertyManagerAction extends PropertyManagerActionBase implements
		java.io.Serializable {

	@In(create=true)
	RoleAction roleAction;
	
	@In(create=true)
	PersonAction personAction;
	
	
	public static final String ROLE_NAME = "pm";

	@Override
	public String save() {
		boolean isNew = getInstance().getId() == null;
		if (isNew) {
			Role role = createRole();
			getInstance().getUser().getRoles().add(role);
		}
		String result = super.save();
		if (isNew){
			personAction.setPerson(getInstance());
			sendMail("/mails/registrationSuccess.xhtml");
		}
		return result;
	}

	private Role createRole() {
		Role role = roleAction.findByName(ROLE_NAME);
		if(role == null) {
			role = new Role();
			role.setName(ROLE_NAME);
			roleAction.persist(role);
		}
		return role;
	}

}
