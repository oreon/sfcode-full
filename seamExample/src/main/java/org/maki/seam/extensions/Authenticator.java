package org.maki.seam.extensions;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.security.Identity;
import org.maki.seam.model.Role;
import org.maki.seam.model.User;
import org.maki.seam.util.UserUtil;

@Name("org.maki.seam.extensions.authenticator")
public class Authenticator {

	@In
	EntityManager entityManager;

	@Out(required = false, scope = ScopeType.SESSION)
	User user;

	public boolean authenticate() {

		Query q = entityManager.createQuery("SELECT u FROM User u "
				+ "WHERE u.emailAddress = #{identity.username} "
				+ "AND u.password = #{identity.password}");

		try {
			user = (User) q.getSingleResult();
			if (user.getRoles() != null) {
				for (Role r : user.getRoles()) {
					Identity.instance().addRole(r.getRoleName());
				}
			}

			UserUtil.setCurrentUserAndIp(user, "192.168.1.105");
			return true;
		} catch (NoResultException nre) {
			return false;
		}
	}
}
