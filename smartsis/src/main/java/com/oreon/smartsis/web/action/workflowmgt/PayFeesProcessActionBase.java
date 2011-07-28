package com.oreon.smartsis.web.action.workflowmgt;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;
import org.witchcraft.jbpm.BaseJbpmProcessAction;

public class PayFeesProcessActionBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	protected com.oreon.smartsis.fees.StudentPaidFee paidFeeToken = new com.oreon.smartsis.fees.StudentPaidFee();

	public void setPaidFeeToken(
			com.oreon.smartsis.fees.StudentPaidFee paidFeeToken) {
		this.paidFeeToken = paidFeeToken;
	}

	public com.oreon.smartsis.fees.StudentPaidFee getPaidFeeToken() {
		return this.paidFeeToken;
	}

	@CreateProcess(definition = "payFees")
	public void startProcess() {

	}

	@StartTask
	public void startPayFees() {

	}

	@EndTask(transition = "proceedToReceiveFees")
	public void proceedToReceiveFeesPayFees() {

	}

	@StartTask
	public void startReceiveFees() {

	}

	@EndTask(transition = "done")
	public void doneReceiveFees() {

	}

}
