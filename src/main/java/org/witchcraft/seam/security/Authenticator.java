package org.witchcraft.seam.security;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.SimpleGroup;
import org.jboss.seam.security.management.IdentityManager;
import org.jboss.seam.security.permission.Permission;
import org.jboss.seam.security.permission.PermissionManager;
import org.jboss.seam.security.permission.RuleBasedPermissionResolver;
import org.jboss.seam.util.Base64;
import org.witchcraft.base.entity.UserUtilAction;

import com.hrb.tservices.domain.offices.Office;
import com.hrb.tservices.domain.users.AppRole;
import com.hrb.tservices.domain.users.AppUser;
import com.hrb.tservices.web.action.users.AppUserAction;

@Name("authenticator")
public class Authenticator {

	@Logger
	Log log;

	@In
	EntityManager entityManager;

	@In
	IdentityManager identityManager;

	@In
	Credentials credentials;

	@In(required = false)
	Actor actor;

	@In
	Identity identity;

	public boolean authenticate() {

		try {
			// String hash = getHash(credentials.getPassword(),
			// credentials.getUsername());
			// System.out.println(hash);
			if (!identityManager.authenticate(credentials.getUsername(),
					credentials.getPassword()))
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		try {

			//PermissionManager.instance().revokePermission(
			//		new Permission(Office.class, "update", new SimpleGroup("partner")));

			AppUser user = (AppUser) entityManager.createQuery(
					"from AppUser where username = :username ").setParameter(
					"username", credentials.getUsername())
			// .setParameter("password", credentials.getPassword())
					.getSingleResult();

			updateActor(user);
			UserUtilAction userUtilAction = (UserUtilAction) Component
					.getInstance("userUtilAction");

			RuleBasedPermissionResolver resolver = RuleBasedPermissionResolver
					.instance();
			if (resolver != null) {
				resolver.getSecurityContext().insert(user);
			}

			userUtilAction.setCurrentUser(user);

			updateLastLogin(user);

			return true;
		} catch (NoResultException ex) {
			return false;
		}

	}

	private void updateLastLogin(AppUser user) {
		user.setLastLogin(new Date());
		AppUserAction userAction = (AppUserAction) Component
				.getInstance("appUserAction");
		userAction.persist(user);
	}

	private void updateActor(AppUser user) {
		if (actor == null)
			return;
		actor.setId(user.getUserName());
		Set<AppRole> roles = user.getAppRoles();
		for (AppRole role : roles) {
			actor.getGroupActorIds().add(role.getName());
		}
	}

	private String getHash(String password, String saltPhrase) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");

		md.update(saltPhrase.getBytes());

		byte[] salt = md.digest();

		md.reset();

		md.update(password.getBytes("UTF-8"));

		md.update(salt);

		byte[] raw = md.digest();

		return new String(Base64.encodeBytes(raw));
	}

}