package com.nas.recovery.web.action.workflows.actionhandlers;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.nas.recovery.web.action.workflows.IssueToken;

public class DeveloperAssignment implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		IssueToken issueToken = (IssueToken) executionContext.getVariable("issueToken");
		if (issueToken.getIssue().getDeveloper() != null)
			assignable.setActorId(issueToken.getIssue().getDeveloper().getUser().getUserName());
		else
			assignable.setPooledActors("developer");
	}

}
