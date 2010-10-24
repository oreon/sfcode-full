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
	public void startFirstTaskTask() {

	}

	@EndTask(transition = "done")
	public void doneFirstTaskTask() {

	}
	@EndTask(transition = "timeout")
	public void timeoutFirstTaskTask() {

	}

	@StartTask
	public void startSecondTask() {

	}

	@EndTask(transition = "proceedTothird")
	public void proceedTothirdSecondTask() {

	}

	@StartTask
	public void startThirdTask() {

	}

	@EndTask(transition = "proceedTofourth")
	public void proceedTofourthThirdTask() {

	}

	@StartTask
	public void startFourthTask() {

	}

	@EndTask(transition = "proceedToend")
	public void proceedToendFourthTask() {

	}

}
