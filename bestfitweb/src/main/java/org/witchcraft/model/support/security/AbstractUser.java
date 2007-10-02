package org.witchcraft.model.support.security;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.Transient;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.witchcraft.model.support.BusinessEntity;


/**
 * @author jsingh
 *
 */
public abstract class AbstractUser extends BusinessEntity implements UserDetails {
	
	 	@Transient
	    public GrantedAuthority[] getAuthorities() {
	 		
	 		Set<AbstractAuthority> userAuthorities = getUserAuthorities();
	 		
	        GrantedAuthority[] grantedAuthorities = new GrantedAuthority[userAuthorities.size()];
	        int counter = 0;
	        for (Iterator<AbstractAuthority> iterator = userAuthorities.iterator(); iterator.hasNext();) {
	        	AbstractAuthority authority = iterator.next();
	            grantedAuthorities[counter++] = new GrantedAuthorityImpl(authority.getAuthority());
	        }
	        return grantedAuthorities;
	    }
	 	
	 	@SuppressWarnings("unchecked")
		public abstract Set getUserAuthorities();

	 	@Transient
		public boolean isAccountNonExpired() {
			return true;
		}

	 	@Transient
		public boolean isAccountNonLocked() {
			return true;
		}

	 	@Transient
		public boolean isCredentialsNonExpired() {
			return true;
		}

	 
	 	
}
