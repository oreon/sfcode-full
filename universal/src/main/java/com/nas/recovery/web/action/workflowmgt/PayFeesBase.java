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

public class PayFeesBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	protected com.oreon.tapovan.domain.PaidFee paidFeeToken = new com.oreon.tapovan.domain.PaidFee();

	public void setPaidFeeToken(com.oreon.tapovan.domain.PaidFee paidFeeToken) {
		this.paidFeeToken = paidFeeToken;
	}

	public com.oreon.tapovan.domain.PaidFee getPaidFeeToken() {
		return this.paidFeeToken;
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
