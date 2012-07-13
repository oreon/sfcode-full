package org.witchcraft.seam.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

public class Restrictions {
	public @Secures
	@Admin
	boolean isAdmin( Identity identity ) {
		return identity.hasRole( "admin", "USERS", "GROUP" ) || identity.hasRole("manager", "USERS", "GROUP" ) || identity.hasRole("support", "USERS", "GROUP" );
	}
	
	public @Secures
	@Authenticated
	boolean isAuthenticated( Identity identity ) {
		return identity.isLoggedIn();
	}
}