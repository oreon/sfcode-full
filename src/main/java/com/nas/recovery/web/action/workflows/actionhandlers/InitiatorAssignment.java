package com.nas.recovery.web.action.workflows.actionhandlers;

import org.jboss.seam.Component;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.nas.recovery.web.action.issues.IssueAction;
import com.nas.recovery.web.action.workflows.IssueToken;

public class InitiatorAssignment implements AssignmentHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3813876488861834230L;

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		IssueAction issueAction = (IssueAction) Component.getInstance("issueAction");
		
		String id = (String) executionContext.getVariable("initiator");
		
	//	IssueToken issueToken = (IssueToken) executionContext.getVariable("issueToken");
	 // if (issueToken.getIssue().getCreatedByUser() != null)
			assignable.setActorId(id); //issueToken.getIssue().getCreatedByUser().getUserName());
		
	}

}
