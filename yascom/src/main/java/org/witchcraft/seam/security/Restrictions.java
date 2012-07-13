package org.witchcraft.seam.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

public class Restrictions {
	public @Secures
	@Admin
	boolean isAdmin( Identity identity ) {
		if(identity.getUser() == null)
			return false;
		System.out.println(identity.getUser().getId()); //  + " " + identity.getRoles().iterator().next().toString()) ;
		return true;
	}
	
	/*
	public @Secures
	@Authenticated
	boolean isAuthenticated( Identity identity ) {
		return identity != null && identity.isLoggedIn();
	}*/
}