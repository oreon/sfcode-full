
	
package com.oreon.cerebrum.web.action.encounter;
	

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.web.action.employee.PhysicianAction;

	
//@Scope(ScopeType.CONVERSATION)
@Name("encounterAction")
public class EncounterAction extends EncounterActionBase implements java.io.Serializable{
	
	@In(create=true)
	PhysicianAction physicianAction;
	
	public EncounterAction(){
		super();
		/*
		Encounter encounter = getInstance();
		if(isNew() && encounter.getListReasons().isEmpty()){
			encounter.getListReasons().add(new Reason());
		}
		*/
	}
	
	@Override
	public String save() {
		//instance.setCreator(physicianAction.getCurrentLoggedInEmployee());
		
		prescriptionAction.getInstance().setPatient(instance.getPatient());
		prescriptionAction.save();
		
		patientAction.setInstance(instance.getPatient());
		patientAction.save();
		
		instance.setPrescription(prescriptionAction.getInstance());
		
		return super.save();
	}
	
}
	