package org.witchcraft.jbpm.mail;

import org.jboss.seam.Component;

import com.pcas.datapkg.users.AppUser;
import com.pcas.datapkg.web.action.users.AppUserAction;




public class IdentityAddressResolver extends org.jbpm.identity.mail.IdentityAddressResolver{

	/**
	 * 
	 */
	private static final long serialVersionUID = -947350246139489225L;
	

	@Override
	public Object resolveAddress(String actorId) {
		AppUserAction userAction = (AppUserAction) Component.getInstance("appUserAction");
		AppUser user = userAction.findByUnqUserName(actorId);
		return user.getEmail();
	}

}
