package com.jonah.mentormatcher.web.action.workflowmgt.approveMentorship.assign;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.jonah.mentormatcher.domain.mentorship.JoinRequest;

public class DecisionMakerAssignment implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		JoinRequest issueToken = (JoinRequest) executionContext.getVariable("processToken");
		assignable.setActorId(issueToken.getMentorshipOffering().getMentor().getAppUser().getUserName());
	}

}
