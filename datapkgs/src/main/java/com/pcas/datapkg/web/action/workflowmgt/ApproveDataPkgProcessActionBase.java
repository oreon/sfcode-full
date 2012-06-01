package com.pcas.datapkg.web.action.workflowmgt;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;
import org.witchcraft.jbpm.BaseJbpmProcessAction;

public class ApproveDataPkgProcessActionBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	protected com.pcas.datapkg.domain.DataPackage processToken = new com.pcas.datapkg.domain.DataPackage();

	public void setProcessToken(com.pcas.datapkg.domain.DataPackage processToken) {
		this.processToken = processToken;
	}

	public com.pcas.datapkg.domain.DataPackage getProcessToken() {
		return this.processToken;
	}

	@CreateProcess(definition = "approveDataPkg", processKey = "#{dataPackageAction.instance.id}")
	public void startProcess() {

	}

	@StartTask
	public void startReviewExam() {

	}

	@EndTask(transition = "askForChanges")
	public void askForChangesReviewExam() {

	}
	@EndTask(transition = "proceedToApprove")
	public void proceedToApproveReviewExam() {

	}
	@EndTask(transition = "proceedToReject")
	public void proceedToRejectReviewExam() {

	}

	@StartTask
	public void startRevise() {

	}

	@EndTask(transition = "proceedToReviewExam")
	public void proceedToReviewExamRevise() {

	}

}
