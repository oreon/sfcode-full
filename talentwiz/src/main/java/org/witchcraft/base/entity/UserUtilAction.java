package org.witchcraft.base.entity;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.talent.users.AppUser;


@Name("userUtilAction")
@Scope(ScopeType.SESSION)
public class UserUtilAction implements Serializable{
	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3320546173691963806L;

	private AppUser currentUser;
	
	@In
	EntityManager entityManager;

	public AppUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(AppUser currentUser) {
		this.currentUser = entityManager.merge(currentUser);	
	}
}
