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

public class BugManagementBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	protected org.wc.trackrite.issues.Issue token = new org.wc.trackrite.issues.Issue();

	public void setToken(org.wc.trackrite.issues.Issue token) {
		this.token = token;
	}

	public org.wc.trackrite.issues.Issue getToken() {
		return this.token;
	}

	@StartTask
	public void startAssignDeveloperTask() {

	}

	@EndTask(transition = "assign")
	public void assignAssignDeveloperTask() {

	}
	@EndTask(transition = "nonissue")
	public void nonissueAssignDeveloperTask() {

	}

	@StartTask
	public void startReviewIssueTask() {

	}

	@EndTask(transition = "reject")
	public void rejectReviewIssueTask() {

	}
	@EndTask(transition = "accept")
	public void acceptReviewIssueTask() {

	}

	@StartTask
	public void startVerifyfixTask() {

	}

	@EndTask(transition = "close")
	public void closeVerifyfixTask() {

	}
	@EndTask(transition = "requestModification")
	public void requestModificationVerifyfixTask() {

	}

	@StartTask
	public void startWorkOnIssueTask() {

	}

	@EndTask(transition = "fixed")
	public void fixedWorkOnIssueTask() {

	}

	@StartTask
	public void startNotifyCloseTask() {

	}

	@EndTask(transition = "close")
	public void closeNotifyCloseTask() {

	}

}
