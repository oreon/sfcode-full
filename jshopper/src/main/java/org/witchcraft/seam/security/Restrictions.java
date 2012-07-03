package org.witchcraft.seam.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;
import org.jboss.seam.security.annotations.SecurityBindingType;
import org.jboss.seam.security.permission.PermissionManager;


public class Restrictions {
	
	
	public @Secures
	@hasPermission
	boolean hasPermission( Identity identity ) {
		
		return true;
		
		//PermissionManager pm;
		//pm.g
		//Permissio
		
		//System.out.println(identity.getUser().getId());
		//return identity.hasPermission( "action", "roleName" );
		
		//identity.
		//return identity.hasRole( "admin", "USERS", "GROUP" );
	}
}