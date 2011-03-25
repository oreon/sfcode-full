package com.pwc.insuranceclaims.web.action.workflowmgt.processClaim.decisions;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

import com.pwc.insuranceclaims.quickclaim.Claim;

public class AutoApproveDecisionHandler implements DecisionHandler {
	
	public String decide(ExecutionContext executionContext) throws Exception {
		Claim claim = (Claim) executionContext.getVariable("processToken");
		return claim.getClaimAmount() < 2000.0 ? "yes" : "no";
	}
}
