package org.witchcraft.base.entity;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.witchcraft.users.IUser;
import org.witchcraft.users.User;


@Name("userUtilAction")
@Scope(ScopeType.SESSION)
public class UserUtilAction {
	
	private IUser currentUser;
	
	@In
	EntityManager entityManager;

	public IUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(IUser currentUser) {
		this.currentUser = entityManager.merge(currentUser);	
	}

}
