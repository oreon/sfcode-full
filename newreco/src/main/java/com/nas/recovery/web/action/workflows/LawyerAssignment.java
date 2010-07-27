package com.nas.recovery.web.action.workflows;


import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import com.nas.recovery.web.action.common.RecoveryToken;

public class LawyerAssignment implements AssignmentHandler{

	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		RecoveryToken recoveryToken = (RecoveryToken) executionContext.getVariable("recoveryToken");
		assignable.setActorId(recoveryToken.getLawyer());
	}

}
