
	
package com.jonah.mentormatcher.web.action.mentorship;
	

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.jonah.mentormatcher.domain.mentorship.JoinRequest;
import com.jonah.mentormatcher.domain.mentorship.MentorshipOffering;
import com.jonah.mentormatcher.web.action.domain.EmployeeAction;
import com.jonah.mentormatcher.web.action.workflowmgt.ApproveMentorshipProcessAction;

	
//@Scope(ScopeType.CONVERSATION)
@Name("mentorshipOfferingAction")
public class MentorshipOfferingAction extends MentorshipOfferingActionBase implements java.io.Serializable{
	
	@In(create=true)
	ApproveMentorshipProcessAction approveMentorshipProcessAction;
	
	
	public void apply(){
		//MentorshipOfferingAction mentorshipOfferingAction = (MentorshipOfferingAction) Component.getInstance("mentorshipOfferingAction");
		//persist();
		EmployeeAction employeeAction = (EmployeeAction) Component.getInstance("employeeAction");
		
		JoinRequest joinRequest = new JoinRequest();
		joinRequest.setMentorshipOffering(entityManager.find(MentorshipOffering.class, getInstance().getId()) );
		joinRequest.setProspectiveMentee(employeeAction.getCurrentLoggedInEmployee());
		
		JoinRequestAction joinRequestAction = (JoinRequestAction) Component.getInstance("joinRequestAction");
		joinRequestAction.persist(joinRequest);
		joinRequestAction.setInstance(joinRequest);
		
		approveMentorshipProcessAction.setProcessToken(joinRequest);
		approveMentorshipProcessAction.startProcess();
	}
	
	@Override
	public String createOffering() {
		save();
		return "success";
	}
	
	/*
	public void setCurrentEntityId(){
		
	}*/
}
	