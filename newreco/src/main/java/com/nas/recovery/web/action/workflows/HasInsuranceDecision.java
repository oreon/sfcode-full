package com.nas.recovery.web.action.workflows;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

import com.nas.recovery.web.action.common.RecoveryToken;

public class HasInsuranceDecision implements DecisionHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1294469042192641230L;

	public String decide(ExecutionContext executionContext) throws Exception {
		return 	((RecoveryToken) executionContext.getVariable("recoveryToken")).hasInsurer();
	}

}
