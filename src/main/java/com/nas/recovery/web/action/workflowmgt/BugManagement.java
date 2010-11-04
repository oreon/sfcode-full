package com.nas.recovery.web.action.workflowmgt;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.CreateProcess;

import com.nas.recovery.web.action.issues.IssueAction;

@Name("bugManagementProcessAction")
//@Scope(ScopeType.CONVERSATION)
public class BugManagement extends BugManagementBase {
	
	@In(create=true)
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

}
