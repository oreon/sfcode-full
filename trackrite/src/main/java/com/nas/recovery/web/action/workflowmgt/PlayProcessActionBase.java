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

public class PlayProcessActionBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@CreateProcess(definition = "play")
	public void startProcess() {

	}

	@StartTask
	public void startFirstTask() {

	}

	@EndTask(transition = "timeout")
	public void timeoutFirstTask() {

	}
	@EndTask(transition = "done")
	public void doneFirstTask() {

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

	@EndTask(transition = "timeout")
	public void timeoutThird() {

	}
	@EndTask(transition = "proceedToFourth")
	public void proceedToFourthThird() {

	}

	@StartTask
	public void startFourth() {

	}

	@EndTask(transition = "proceedToEnd")
	public void proceedToEndFourth() {

	}

}
