package org.witchcraft.base.entity;

import javax.persistence.EntityManager;

import org.cerebrum.domain.users.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;


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

	/*
    private static ThreadLocal<User> currentUser = new ThreadLocal<User>();
    private static ThreadLocal<String> ipAddress = new ThreadLocal<String>();
    
    public static void setCurrentUserAndIp(User user, String ip) {
        currentUser.set(user);
        ipAddress.set(ip);
    }
    
    public static User getCurentUser() { return currentUser.get(); }
    public static String getIpAddress() { return ipAddress.get(); }*/
}
