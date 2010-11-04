package com.nas.recovery.web.action.workflowmgt.play.assign;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.Token;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

public class CreateTasks implements ActionHandler {
	
	public void execute(ExecutionContext executionContext) throws Exception {
		Token token = executionContext.getToken();
		TaskMgmtInstance tmi = executionContext.getTaskMgmtInstance();

		TaskNode taskNode = (TaskNode) executionContext.getNode();
		System.out.println("We are in actionhandler");
		
	}
}