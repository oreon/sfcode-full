package org.witchcraft.base.entity;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;

import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;
import org.wcdemo.xstories.TeamMember;

public class EntityListener {

	@PrePersist
	public void setDatesAndUser(BusinessEntity modelBase) {
		Date now = new Date();
		if (modelBase.getDateCreated() == null) {
			modelBase.setDateCreated(now);
		}
		
		modelBase.setDateUpdated(now);

		TeamMember currentUser = UserUtil.getCurentUser();

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
