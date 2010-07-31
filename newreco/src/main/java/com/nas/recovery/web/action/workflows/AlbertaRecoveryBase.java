package com.nas.recovery.web.action.workflows;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;

class AlbertaRecoveryBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@CreateProcess(definition = "albertaRecovery")
	public void startProcess() {

	}

	@StartTask
	public void startCheckConflictTask() {

	}

	@EndTask(transition = "conflict")
	public void conflictCheckConflictTask() {

	}
	@EndTask(transition = "accept")
	public void acceptCheckConflictTask() {

	}

	@StartTask
	public void startCheckVacancyTask() {

	}

	@EndTask(transition = "checked")
	public void checkedCheckVacancyTask() {

	}

	@StartTask
	public void startServeFirstNoticeTask() {

	}

	@EndTask(transition = "served")
	public void servedServeFirstNoticeTask() {

	}

	@StartTask
	public void startNotifyInsurerTask() {

	}

	@EndTask(transition = "proceed")
	public void proceedNotifyInsurerTask() {

	}

	@StartTask
	public void startFinalReviewTask() {

	}

	@EndTask(transition = "doReview")
	public void doReviewFinalReviewTask() {

	}

	@StartTask
	public void startAssignLawyerTask() {

	}

	@EndTask(transition = "assignLawyer")
	public void assignLawyerAssignLawyerTask() {

	}

}
