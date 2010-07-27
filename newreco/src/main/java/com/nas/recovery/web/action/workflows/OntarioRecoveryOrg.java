package com.nas.recovery.web.action.workflows;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;


// @Scope(ScopeType.CONVERSATION)
class OntarioRecoveryOrg extends BaseJbpmProcessAction {



	@StartTask
	public void startReviewAssignmentTask() {

	}

	@EndTask(transition = "accept")
	public void acceptReviewAssignmentTask() {

	}
	@EndTask(transition = "conflict")
	public void conflictReviewAssignmentTask() {

	}

	@StartTask
	public void startChooseLawyerTask() {

	}

	@EndTask(transition = "assign")
	public void assignChooseLawyerTask() {

	}

	@StartTask
	public void startServeInitialNoticeTask() {

	}

	@EndTask(transition = "serve")
	public void serveServeInitialNoticeTask() {

	}
	@EndTask(transition = "")
	public void ServeInitialNoticeTask() {

	}

	@StartTask
	public void startServerreminderTask() {

	}

	@EndTask(transition = "serveReminder")
	public void serveReminderServerreminderTask() {

	}

	@StartTask
	public void startFinalReviewTask() {

	}

	@EndTask(transition = "finalReview")
	public void finalReviewFinalReviewTask() {

	}

}
