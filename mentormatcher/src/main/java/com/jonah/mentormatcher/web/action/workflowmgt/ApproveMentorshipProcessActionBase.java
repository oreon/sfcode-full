package com.jonah.mentormatcher.web.action.workflowmgt;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;
import org.witchcraft.jbpm.BaseJbpmProcessAction;

public class ApproveMentorshipProcessActionBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	protected com.jonah.mentormatcher.domain.mentorship.JoinRequest processToken = new com.jonah.mentormatcher.domain.mentorship.JoinRequest();

	public void setProcessToken(
			com.jonah.mentormatcher.domain.mentorship.JoinRequest processToken) {
		this.processToken = processToken;
	}

	public com.jonah.mentormatcher.domain.mentorship.JoinRequest getProcessToken() {
		return this.processToken;
	}

	@CreateProcess(definition = "approveMentorship", processKey = "#{joinRequestAction.instance.id}")
	public void startProcess() {

	}

	@StartTask
	public void startReviewRequest() {

	}

	@EndTask(transition = "proceedToApprove")
	public void proceedToApproveReviewRequest() {

	}
	@EndTask(transition = "proceedToReject")
	public void proceedToRejectReviewRequest() {

	}
	@EndTask(transition = "askForMoreInfo")
	public void askForMoreInfoReviewRequest() {

	}

	@StartTask
	public void startRevise() {

	}

	@EndTask(transition = "provideInfo")
	public void provideInfoRevise() {

	}

}
