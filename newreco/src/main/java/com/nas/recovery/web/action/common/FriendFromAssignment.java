package com.nas.recovery.web.action.common;


import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;


public class FriendFromAssignment implements AssignmentHandler{

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		// TODO Auto-generated method stub
		FriendShipRequest fr = (FriendShipRequest) executionContext.getVariable("friendshipRequest");
		//assignable.setActorId((String) executionContext.getVariable("to"));
		assignable.setActorId(fr.getFrom());
		
	}

}
