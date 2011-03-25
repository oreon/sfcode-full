package com.pwc.insuranceclaims.web.action.workflowmgt.processClaim.assign;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.pwc.insuranceclaims.quickclaim.Claim;

public class ClaimantAssignment implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		Claim issueToken = (Claim) executionContext.getVariable("processToken");

		assignable.setActorId(issueToken.getCreatedByUser().getUserName());
	}

}
