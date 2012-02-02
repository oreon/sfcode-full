package com.oreon.talent.web.action.workflowmgt;

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
	protected com.oreon.talent.domain.Exam processToken = new com.oreon.talent.domain.Exam();

	public void setProcessToken(com.oreon.talent.domain.Exam processToken) {
		this.processToken = processToken;
	}

	public com.oreon.talent.domain.Exam getProcessToken() {
		return this.processToken;
	}

	@CreateProcess(definition = "approveExam", processKey = "#{examAction.instance.id}")
	public void startProcess() {

	}

	@StartTask
	public void startReviewExam() {

	}

	@EndTask(transition = "proceedToReject")
	public void proceedToRejectReviewExam() {

	}
	@EndTask(transition = "askForChanges")
	public void askForChangesReviewExam() {

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
