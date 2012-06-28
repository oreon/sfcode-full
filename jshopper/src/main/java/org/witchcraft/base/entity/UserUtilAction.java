package org.witchcraft.base.entity;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject; import javax.ejb.Stateful;
import javax.persistence.EntityManager;


@SessionScoped
public class UserUtilAction implements Serializable{
	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3320546173691963806L;

	
	
	@Inject
	EntityManager entityManager;
	

	/*
	private AppUser currentUser;
	
	public AppUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(AppUser currentUser) {
		this.currentUser = entityManager.merge(currentUser);	
	}*/
}
