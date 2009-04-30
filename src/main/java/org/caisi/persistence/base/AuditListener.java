package org.caisi.persistence.base;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

public class AuditListener {

	@PostPersist
	public void doAudit(BusinessEntity entity) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (!(entity instanceof Auditable))
			return;

		System.out.println(auth.getName() + " is adding/updating entity :" + entity);

	}

	@PostRemove
	public void doRemove(BusinessEntity entity) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (!(entity instanceof Auditable))
			return;

		System.out.println(auth.getName() + " is removing entity :" + entity);
	}

}
