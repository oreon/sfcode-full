package org.witchcraft.users.idreslover;

import org.jbpm.mail.AddressResolver;

public class IdentityAddressResolver implements AddressResolver{

	@Override
	public Object resolveAddress(String actorId) {
		// TODO Auto-generated method stub
		System.out.println("resolving id for " + actorId);
		return "singhjess@gmail.com";
	}

}
