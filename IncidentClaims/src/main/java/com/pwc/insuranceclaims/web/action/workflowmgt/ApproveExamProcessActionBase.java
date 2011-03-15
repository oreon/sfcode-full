package com.pwc.insuranceclaims.web.action.workflowmgt;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;
import org.witchcraft.jbpm.BaseJbpmProcessAction;

public class ApproveExamProcessActionBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	protected com.pwc.insuranceclaims.quickclaim.Claim processToken = new com.pwc.insuranceclaims.quickclaim.Claim();

	public void setProcessToken(
			com.pwc.insuranceclaims.quickclaim.Claim processToken) {
		this.processToken = processToken;
	}

	public com.pwc.insuranceclaims.quickclaim.Claim getProcessToken() {
		return this.processToken;
	}

	@CreateProcess(definition = "approveExam", processKey = "#{claimAction.instance.id}")
	public void startProcess() {

	}

	@StartTask
	public void startReviewExam() {

	}

	@EndTask(transition = "askForChanges")
	public void askForChangesReviewExam() {

	}
	@EndTask(transition = "proceedToReject")
	public void proceedToRejectReviewExam() {

	}
	@EndTask(transition = "proceedToApprove")
	public void proceedToApproveReviewExam() {

	}

	@StartTask
	public void startRevise() {

	}

	@EndTask(transition = "proceedToReviewExam")
	public void proceedToReviewExamRevise() {

	}

}
