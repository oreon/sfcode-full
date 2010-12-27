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

public class PlayBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@StartTask
	public void startFirstTask() {

	}

	@EndTask(transition = "done")
	public void doneFirstTask() {

	}
	@EndTask(transition = "timeout")
	public void timeoutFirstTask() {

	}

	@StartTask
	public void startSecond() {

	}

	@EndTask(transition = "proceedToThird")
	public void proceedToThirdSecond() {

	}

	@StartTask
	public void startThird() {

	}

	@EndTask(transition = "proceedToFourth")
	public void proceedToFourthThird() {

	}
	@EndTask(transition = "timeout")
	public void timeoutThird() {

	}

	@StartTask
	public void startFourth() {

	}

	@EndTask(transition = "proceedToEnd")
	public void proceedToEndFourth() {

	}

}
