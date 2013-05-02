package org.witchcraft.seam.security;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.permission.RuleBasedPermissionResolver;
import org.witchcraft.base.entity.UserUtilAction;

import com.oreon.cerebrum.users.AppRole;
import com.oreon.cerebrum.users.AppUser;

@Name("authenticator")
public class Authenticator {
	
	@Logger Log log;

	@In
	EntityManager entityManager;

	@In
	Credentials credentials;
	
	@In(required=false)
	Actor actor;

	@In
	Identity identity;

	public boolean authenticate() {

		try {
			
			Session session = (Session)entityManager.getDelegate();
			
			session.disableFilter("tenantFilterDef");

			AppUser user = (AppUser) entityManager
					.createQuery(
					"from AppUser where username = :username and password = :password")
					.setParameter("username", credentials.getUsername())
					.setParameter("password", credentials.getPassword())
					.getSingleResult();
			
			
			if(!user.getEnabled()){
				//add message not enalbed
				//return false;
			}

			if (user.getAppRoles() != null) {
				Set<AppRole> roles = user.getAppRoles();
				for (AppRole role : roles) {
					identity.addRole(role.getName());
				}
			}else{
				log.warn("no role found for user " + user.getUserName());
			}
			updateActor(user);
			UserUtilAction userUtilAction = (UserUtilAction)Component.getInstance("userUtilAction");
			
			RuleBasedPermissionResolver resolver = RuleBasedPermissionResolver.instance();
			if(resolver != null) {
				resolver.getSecurityContext().insert(user);
			}

			userUtilAction.setCurrentUser(user);
			
			
			session.enableFilter("tenantFilterDef");
			/*
			user.setLastLogin(new Date());
			UserAction userAction = (UserAction) Component.getInstance("userAction");
			userAction.setInstance(user);
			userAction.save();*/
			
			return true;
		}

		catch (NoResultException ex) {

			return false;

		}

	}
	
	private void updateActor(AppUser user) {
		if(actor == null)
			return;
		actor.setId(user.getUserName());
		Set<AppRole> roles = user.getAppRoles();
		for (AppRole role : roles) {
			actor.getGroupActorIds().add( role.getName() );
		}
	}

}