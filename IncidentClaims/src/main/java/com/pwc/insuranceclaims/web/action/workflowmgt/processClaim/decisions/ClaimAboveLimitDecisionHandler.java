package com.pwc.insuranceclaims.web.action.workflowmgt.processClaim.decisions;

import org.jboss.seam.Component;
import org.jbpm.graph.exe.ExecutionContext;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

import com.pwc.insuranceclaims.quickclaim.Claim;

public class ClaimAboveLimitDecisionHandler implements DecisionHandler {
	
	public String decide(ExecutionContext executionContext) throws Exception {
		Claim claim = (Claim) executionContext.getVariable("processToken");
		return claim.getClaimAmount() < 3000 ? "aboveLimitNo" : "aboveLimitYes";

	}
}
