package org.witchcraft.seam.security;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.witchcraft.base.entity.UserUtilAction;

import com.wc.jshopper.users.Role;
import com.wc.jshopper.users.User;

@Name("authenticator")
public class Authenticator {
	
	@Logger Log log;

	@In
	EntityManager entityManager;

	@In
	Credentials credentials;

	@In
	Identity identity;

	public boolean authenticate() {

		try {

			User user = (User) entityManager
					.createQuery(
					"from User where username = :username and password = :password")
					.setParameter("username", credentials.getUsername())
					.setParameter("password", credentials.getPassword())
					.getSingleResult();

			if (user.getRoles() != null) {
				Set<Role> roles = user.getRoles();
				for (Role role : roles) {
					identity.addRole(role.getName());
				}
			}else{
				log.warn("no role found for user " + user.getUserName());
			}
			
			UserUtilAction userUtilAction = (UserUtilAction)Component.getInstance("userUtilAction");
			userUtilAction.setCurrentUser(user);
			return true;
		}

		catch (NoResultException ex) {

			return false;

		}

	}

}