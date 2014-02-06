
	
package com.oreon.cerebrum.web.action.facility;
	

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.UserUtilAction;

import com.oreon.cerebrum.web.action.employee.PhysicianAction;

	
//@Scope(ScopeType.CONVERSATION)
@Name("facilityAction")
public class FacilityAction extends FacilityActionBase implements java.io.Serializable{
	
	@In(create=true)
	PhysicianAction physicianAction;
	
	@In(create=true)
	UserUtilAction userUtilAction;
	
	
	public String register(){
		save();
		instance.setTenant(instance.getId());
		//need to do another save to update facility tenant
		save();
		userUtilAction.setCurrentFacility(instance);
		//physicianAction.getInstance().setTenant(getInstance().getId());
		physicianAction.getInstance().setFacility(getInstance());
		
		physicianAction.getInstance().getAppUser().setEnabled(true);
		
		physicianAction.save();
		
		return "success";
	}
	
}
	