package org.witchcraft.jbpm.mail;

import org.jboss.seam.Component;

import com.nas.recovery.web.action.users.UserAction;



public class IdentityAddressResolver extends org.jbpm.identity.mail.IdentityAddressResolver{

	/**
	 * 
	 */
	private static final long serialVersionUID = -947350246139489225L;
	
	

	@Override
	public Object resolveAddress(String actorId) {
		// TODO Auto-generated method stub
		System.out.println("resolving id for " + actorId);
		UserAction userAction = (UserAction) Component.getInstance("userAction");
		userAction.ex
		return "singhjess@gmail.com";
	}

}
