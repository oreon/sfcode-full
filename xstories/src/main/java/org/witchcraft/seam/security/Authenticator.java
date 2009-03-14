package org.witchcraft.seam.security;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.wcdemo.xstories.ApplicationRole;
import org.wcdemo.xstories.ApplicationUser;

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

			ApplicationUser user = (ApplicationUser) entityManager
					.createQuery(
					"from TeamMember where username = :username and password = :password")
					.setParameter("username", credentials.getUsername())
					.setParameter("password", credentials.getPassword())
					.getSingleResult();

			if (user.getApplicationRole() != null) {
				identity.addRole(user.getApplicationRole().getName());
				log.info("adding role " + user.getApplicationRole().getName() + "  to " + user.getUserName());
			}else{
				log.warn("no role found for user " + user.getUserName());
			}

			return true;

		}

		catch (NoResultException ex) {

			return false;

		}

	}

}