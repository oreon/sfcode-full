
	
package com.oreon.cerebrum.web.action.encounter;
	

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.web.action.employee.EmployeeAction;
import com.oreon.cerebrum.web.action.employee.PhysicianAction;

	
//@Scope(ScopeType.CONVERSATION)
@Name("encounterAction")
public class EncounterAction extends EncounterActionBase implements java.io.Serializable{
	
	@In(create=true)
	PhysicianAction physicianAction;
	
	@Override
	public String save() {
		instance.setCreator(physicianAction.getCurrentLoggedInEmployee());
		
	
		//prescriptionAction.save();
		//instance.setPrescription(prescriptionAction.getInstance());
		
		return super.save();
	}
	
}
	