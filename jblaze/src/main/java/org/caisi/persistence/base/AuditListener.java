package org.caisi.persistence.base;

import javax.persistence.PostPersist;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

public class AuditListener {
	
	@PostPersist
	public void doAudit(BusinessEntity entity){
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		if(!(entity instanceof Auditable))
			return;
			
		System.out.println(auth.getName() + " is auditing entity :" + entity);
		 
	}

}
