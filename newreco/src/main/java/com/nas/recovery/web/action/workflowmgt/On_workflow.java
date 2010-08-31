
	
package com.nas.recovery.web.action.workflowmgt;


	import org.jboss.seam.ScopeType;
	import org.jboss.seam.annotations.In;
	import org.jboss.seam.annotations.Name;
	import org.jboss.seam.annotations.Out;
	import org.jboss.seam.annotations.bpm.CreateProcess;
	import org.jboss.seam.annotations.bpm.EndTask;
	import org.jboss.seam.annotations.bpm.StartTask;
	import org.jbpm.JbpmContext;
	import org.witchcraft.jbpm.BaseJbpmProcessAction;


@Name("on_workflowProcessAction")	
//@Scope(ScopeType.CONVERSATION)
public class On_workflow extends On_workflowBase {

	@CreateProcess(definition = "on_workflow")
	public void startProcess() {
	
	}
	
}
	