package org.witchcraft.seam.security;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.cerebrum.domain.users.Role;
import org.cerebrum.domain.users.User;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.witchcraft.base.entity.UserUtil;

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
				//log.info("adding role " + user.getApplicationRole().getName() + "  to " + user.getUserName());
			}else{
				log.warn("no role found for user " + user.getUserName());
			}
				
			UserUtil.setCurrentUserAndIp(user, "192.144.144.0");
			return true;

		}

		catch (NoResultException ex) {

			return false;

		}

	}

}