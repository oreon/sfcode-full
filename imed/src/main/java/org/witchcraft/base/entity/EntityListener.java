package org.witchcraft.base.entity;

import java.util.Date;

import javax.persistence.PrePersist;

import org.cerebrum.domain.users.User;

public class EntityListener {

	@PrePersist
	public void setDatesAndUser(BusinessEntity modelBase) {
		Date now = new Date();
		if (modelBase.getDateCreated() == null) {
			modelBase.setDateCreated(now);
		}
		
		modelBase.setDateUpdated(now);

		User currentUser = UserUtil.getCurentUser();

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
