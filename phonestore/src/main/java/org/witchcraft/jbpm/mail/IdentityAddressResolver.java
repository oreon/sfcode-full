package org.witchcraft.jbpm.mail;

import org.jboss.seam.Component;
import org.witchcraft.users.AppUser;
import org.witchcraft.users.action.AppUserAction;




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
