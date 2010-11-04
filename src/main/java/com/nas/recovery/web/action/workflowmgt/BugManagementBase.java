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
	public void startAssignDeveloper() {

	}

	@EndTask(transition = "nonissue")
	public void nonissueAssignDeveloper() {

	}
	@EndTask(transition = "assign")
	public void assignAssignDeveloper() {

	}

	@StartTask
	public void startReviewIssue() {

	}

	@EndTask(transition = "accept")
	public void acceptReviewIssue() {

	}
	@EndTask(transition = "reject")
	public void rejectReviewIssue() {

	}

	@StartTask
	public void startVerifyfix() {

	}

	@EndTask(transition = "requestModification")
	public void requestModificationVerifyfix() {

	}
	@EndTask(transition = "close")
	public void closeVerifyfix() {

	}

	@StartTask
	public void startWorkOnIssue() {

	}

	@EndTask(transition = "fixed")
	public void fixedWorkOnIssue() {

	}

	@StartTask
	public void startNotifyClose() {

	}

	@EndTask(transition = "close")
	public void closeNotifyClose() {

	}

	@StartTask
	public void startNotifyCreate() {

	}

	@EndTask(transition = "proceedToReviewIssue")
	public void proceedToReviewIssueNotifyCreate() {

	}

}
