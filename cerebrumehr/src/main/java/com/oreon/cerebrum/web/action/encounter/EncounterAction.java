
	
package com.oreon.cerebrum.web.action.encounter;
	

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Identity;
import org.witchcraft.base.entity.UserUtilAction;
import org.witchcraft.exceptions.BusinessException;

import com.oreon.cerebrum.codes.SimpleCode;
import com.oreon.cerebrum.web.action.employee.PhysicianAction;

	
//@Scope(ScopeType.CONVERSATION)
@Name("encounterAction")
public class EncounterAction extends EncounterActionBase implements java.io.Serializable{
	
	@In(create=true)
	PhysicianAction physicianAction;
	
	@In(create=true)
	UserUtilAction userUtilAction;
	
	private List<SimpleCode> selectedSimpleCodes = new ArrayList<SimpleCode>();
	
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
	public String save(boolean endConversation) {
		instance.setCreator(userUtilAction.getCurrentLoggedInEmployee());
		
		if (getInstance().getPatient() == null) {
			if(patientAction.getInstance() == null || patientAction.getInstance().getId() == null)
				throw new BusinessException("Must Select a patient");
			getInstance().setPatient(patientAction.getInstance());
		}
		
		
		prescriptionAction.getInstance().setPatient(instance.getPatient());
		prescriptionAction.save();
		
		/*
		patientAction.setInstance(instance.getPatient()); 	
		patientAction.save(endConversation);
		*/
		
		instance.setPrescription(prescriptionAction.getInstance());
		//getInstance().setCreatedByUser(userUtilAction.getCurrentUser());
		return super.save(endConversation);
	}

	public void setSelectedSimpleCodes(List<SimpleCode> selectedSimpleCodes) {
		this.selectedSimpleCodes = selectedSimpleCodes;
	}

	public List<SimpleCode> getSelectedSimpleCodes() {
		return selectedSimpleCodes;
	}
	
}
	