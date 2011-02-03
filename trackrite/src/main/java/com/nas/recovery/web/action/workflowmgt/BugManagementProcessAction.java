
	
package com.nas.recovery.web.action.workflowmgt;


	import org.jboss.seam.ScopeType;
	import org.jboss.seam.annotations.In;
	import org.jboss.seam.annotations.Name;
	import org.jboss.seam.annotations.Out;
	import org.jboss.seam.annotations.bpm.CreateProcess;
	import org.jboss.seam.annotations.bpm.EndTask;
	import org.jboss.seam.annotations.bpm.StartTask;
	import org.jbpm.JbpmContext;
import org.wc.trackrite.issues.Issue;
import org.witchcraft.jbpm.BaseJbpmProcessAction;

import com.nas.recovery.web.action.issues.IssueAction;


@Name("bugManagementProcessAction")	
//@Scope(ScopeType.CONVERSATION)
public class BugManagementProcessAction extends BugManagementProcessActionBase {

	@In(create = true)
	IssueAction issueAction;

	@CreateProcess(definition = "BugManagement", processKey = "#{issueAction.instance.id}")
	public void startProcess() {

	}

	@Override
	public String assignToCurrentActor() {
		String result = super.assignToCurrentActor();
		issueAction.updateDeveloper();
		return result;
	}

	public String reassign() {

		return "";
	}

	@Override
	public String getTaskForm() {
		issueAction.load(((Issue) task.getVariable("token")).getId());
		return super.getTaskForm();
	}

	@Override
	public void assignAssignDeveloper() {
		// String dev =
		// issueAction.getInstance().getDeveloper().getUser().getUserName();
		task.getProcessInstance().getTaskMgmtInstance().getSwimlaneInstance(
				"developer").setActorId(
				issueAction.getInstance().getDeveloper().getUser()
						.getUserName());
		
		issueAction.save();
		super.assignAssignDeveloper();
	}
}
