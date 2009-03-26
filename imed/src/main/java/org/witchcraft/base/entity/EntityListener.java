package org.witchcraft.base.entity;

import java.util.Date;

import javax.persistence.PrePersist;

import org.cerebrum.domain.users.User;
import org.jboss.seam.Component;

public class EntityListener {

	@PrePersist
	public void setDatesAndUser(BusinessEntity modelBase) {
		Date now = new Date();
		if (modelBase.getDateCreated() == null) {
			modelBase.setDateCreated(now);
		}
		
		modelBase.setDateUpdated(now);
		
		UserUtilAction userUtilAction = (UserUtilAction)Component.getInstance("userUtilAction");
		
		User currentUser = userUtilAction.getCurrentUser();

		if (currentUser != null) {
			//Identity identity  = (Identity) Component.getInstance("identity");
			//identity.getCredentials().getUsername()
			if (modelBase.getCreatedByUser() == null) {
				modelBase.setCreatedByUser(currentUser);
			}
			//modelBase.setUpdatedByUser(currentUser);
		}

	}

}
