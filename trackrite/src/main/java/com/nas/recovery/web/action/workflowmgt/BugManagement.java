package com.nas.recovery.web.action.workflowmgt;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.wc.trackrite.issues.Issue;
import org.witchcraft.base.entity.BusinessEntity;

import com.nas.recovery.web.action.issues.IssueAction;

@Name("bugManagementProcessAction")
// @Scope(ScopeType.CONVERSATION)
public class BugManagement extends BugManagementBase {

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
