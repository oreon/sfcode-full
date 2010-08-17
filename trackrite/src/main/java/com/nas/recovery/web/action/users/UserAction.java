package com.nas.recovery.web.action.users;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.wc.trackrite.users.Role;

//@Scope(ScopeType.CONVERSATION)
@Name("userAction")
public class UserAction extends UserActionBase implements java.io.Serializable {
	
	public List<Role> getAvailableRoles() {
		if(availableRoles == null){
			init();
		}
		return availableRoles;
	}

	public void setAvailableRoles(List<Role> availableRoles) {
		this.availableRoles = availableRoles;
	}
	
	

	List<Role>  availableRoles = null;
	List<Role>  listRoles = null;
	
	void initListRoles() {
		listRoles = new ArrayList<Role>();
		
		if(instance == null){
			instance = loadFromId(1L);
		}
		
		if (getInstance().getRoles().isEmpty()) {

		} else
			listRoles.addAll(getInstance().getRoles());
	}

	public List<Role> getListRoles() {
		if (listRoles == null || listRoles.isEmpty()) {
			initListRoles();
		}
		return listRoles;
	}

	@Begin
	public String init() {
		if(instance == null){
			instance = loadFromId(1L);
		}
		availableRoles = getEntityManager().createQuery("select r from Role r").getResultList();
		availableRoles.removeAll(getInstance().getRoles());
		return null;
	}

}
