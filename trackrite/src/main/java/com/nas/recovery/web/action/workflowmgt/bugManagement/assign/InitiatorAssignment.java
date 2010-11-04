
	package com.nas.recovery.web.action.workflowmgt.bugManagement.assign;
	
	import org.jboss.seam.Component;
	import org.jbpm.graph.exe.ExecutionContext;
	import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;
import org.wc.trackrite.issues.Issue;

import com.nas.recovery.web.action.issues.IssueAction;
	
	
	public class InitiatorAssignment implements AssignmentHandler {
	
		public void assign(Assignable assignable, ExecutionContext executionContext)
				throws Exception {
			Issue issueToken = (Issue) executionContext.getVariable("token");
			assignable.setActorId(issueToken.getCreatedByUser().getUserName()); //i
		}
		
	}
 