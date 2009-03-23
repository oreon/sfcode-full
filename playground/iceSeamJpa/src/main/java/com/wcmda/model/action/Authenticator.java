package com.wcmda.model.action;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jbpm.graph.exe.ProcessInstance;

import com.wcmda.model.User;

@Name("authenticator")
@Scope(ScopeType.SESSION)
public class Authenticator {
	@Logger
	Log log;

	@In
	Identity identity;

	@In
	private Actor actor;

	@In
	EntityManager entityManager;

	@Out(required = true, scope = ScopeType.SESSION)
	User user;

	public boolean authenticate() {
		// identity.getCredentials().getUsername()
		// ProcessInstance
		org.jbpm.graph.exe.ProcessInstance pi = new ProcessInstance();
		
		
		Query q = entityManager.createQuery("SELECT u FROM User u "
				+ "WHERE u.username = #{identity.username} "
				+ "AND u.password = #{identity.password}");

		log.info("authenticating #identity.credentials.username ");
		try {
			user = (User) q.getSingleResult();
			actor.setId(identity.getCredentials().getUsername());

			return true;
		} catch (NoResultException nre) {
			return false;
		}
	}
}
