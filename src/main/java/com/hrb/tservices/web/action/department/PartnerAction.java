
	
package com.hrb.tservices.web.action.department;
	

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;

	
//@Scope(ScopeType.CONVERSATION)
@Name("partnerAction")
public class PartnerAction extends PartnerActionBase implements java.io.Serializable{
	
	@Restrict("#{s:hasPermission('update','partner')}")
	public void updatePartner(){
		System.out.println("ALLOWED");
	}
	
}
	