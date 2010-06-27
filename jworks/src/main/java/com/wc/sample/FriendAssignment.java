package com.wc.sample;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

public class FriendAssignment implements AssignmentHandler{

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		// TODO Auto-generated method stub
		assignable.setActorId((String) executionContext.getVariable("to"));
		
	}

}
