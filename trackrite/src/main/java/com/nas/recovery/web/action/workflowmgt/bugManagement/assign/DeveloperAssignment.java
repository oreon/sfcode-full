
	package com.nas.recovery.web.action.workflowmgt.bugManagement.assign;
	
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;
import org.wc.trackrite.issues.Issue;
	
	
	public class DeveloperAssignment implements AssignmentHandler {
	
		public void assign(Assignable assignable, ExecutionContext executionContext)
				throws Exception {
			
			Issue issueToken = (Issue) executionContext.getVariable("token");
			if (issueToken.getDeveloper() != null)
				assignable.setActorId(issueToken.getDeveloper().getUser().getUserName());
			else
				assignable.setPooledActors(new String[]{"developer"});
				
		}
		
	}
 