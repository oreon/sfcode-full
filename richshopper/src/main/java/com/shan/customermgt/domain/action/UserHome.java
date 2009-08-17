package com.shan.customermgt.domain.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.management.UserRoles;
import org.jboss.seam.framework.EntityHome;
import org.witchcraft.users.Role;
import org.witchcraft.users.User;

import com.shan.customermgt.domain.Account;

@Name("userHome")
public class UserHome extends EntityHome<User> {

	@In(create = true)
	UserHome userHome;

	public void setUserId(Long id) {
		setId(id);
	}

	public Long getUserId() {
		return (Long) getId();
	}

	@Override
	protected User createInstance() {
		User user = new User();
		return user;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public User getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/*
	public List<Account> getAccounts() {
		return getInstance() == null ? null : new ArrayList<Account>(
				getInstance().getAccounts());
	}
	
	
	public List<Role> getRoles() {
		return getInstance() == null ? null : new ArrayList<Role>(getInstance()
				.getRoles());
	}
	
	public List<UserRoles> getUserRoleses() {
		return getInstance() == null ? null : new ArrayList<UserRoles>(
				getInstance().getUserRoleses());
	}
	public List<User> getUsers() {
		return getInstance() == null ? null : new ArrayList<User>(getInstance()
				.getUsers());
	}*/

}
