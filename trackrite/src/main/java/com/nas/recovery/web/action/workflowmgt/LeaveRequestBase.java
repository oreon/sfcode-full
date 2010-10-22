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

public class LeaveRequestBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@StartTask
	public void startProvideDetailsTask() {

	}

	@EndTask(transition = "submit")
	public void submitProvideDetailsTask() {

	}
	@EndTask(transition = "retract")
	public void retractProvideDetailsTask() {

	}

	@StartTask
	public void startReviewRequestTask() {

	}

	@EndTask(transition = "moreDetails")
	public void moreDetailsReviewRequestTask() {

	}
	@EndTask(transition = "accept")
	public void acceptReviewRequestTask() {

	}
	@EndTask(transition = "reject")
	public void rejectReviewRequestTask() {

	}

}
