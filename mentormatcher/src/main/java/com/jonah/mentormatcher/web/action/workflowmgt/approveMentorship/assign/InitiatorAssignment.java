package com.jonah.mentormatcher.web.action.workflowmgt.approveMentorship.assign;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.jonah.mentormatcher.domain.mentorship.JoinRequest;
import com.jonah.mentormatcher.domain.users.AppUser;

public class InitiatorAssignment implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		JoinRequest issueToken = (JoinRequest) executionContext
				.getVariable("processToken");
		AppUser user = issueToken.getCreatedByUser();
		if (issueToken != null)
			assignable.setActorId(issueToken.getCreatedByUser().getUserName()); // i
	}

}
