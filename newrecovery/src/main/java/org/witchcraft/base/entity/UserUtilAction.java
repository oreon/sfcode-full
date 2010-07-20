package org.witchcraft.base.entity;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.nas.recovery.domain.users.User;


@Name("userUtilAction")
@Scope(ScopeType.SESSION)
public class UserUtilAction {
	
	private User currentUser;
	
	@In
	EntityManager entityManager;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = entityManager.merge(currentUser);	
	}

}
