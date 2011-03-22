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

public class ProcessClaimProcessActionBase extends BaseJbpmProcessAction
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

	@CreateProcess(definition = "ProcessClaim", processKey = "#{claimAction.instance.id}")
	public void startProcess() {

	}

	@StartTask
	public void startReviewDecision() {

	}

	@EndTask(transition = "disagreeWithApproval")
	public void disagreeWithApprovalReviewDecision() {

	}
	@EndTask(transition = "approve")
	public void approveReviewDecision() {

	}

	@StartTask
	public void startReviewQuestion() {

	}

	@EndTask(transition = "returnAnswer")
	public void returnAnswerReviewQuestion() {

	}

	@StartTask
	public void startReviewClaim() {

	}

	@EndTask(transition = "askForMoreInfo")
	public void askForMoreInfoReviewClaim() {

	}
	@EndTask(transition = "proceedToReject")
	public void proceedToRejectReviewClaim() {

	}
	@EndTask(transition = "askQuestion")
	public void askQuestionReviewClaim() {

	}
	@EndTask(transition = "approve")
	public void approveReviewClaim() {

	}

	@StartTask
	public void startProvideInformation() {

	}

	@EndTask(transition = "provideMoreInfo")
	public void provideMoreInfoProvideInformation() {

	}

}
