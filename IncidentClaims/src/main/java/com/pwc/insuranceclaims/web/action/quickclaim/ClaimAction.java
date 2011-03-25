package com.pwc.insuranceclaims.web.action.quickclaim;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jbpm.graph.exe.ExecutionContext;
import org.witchcraft.exceptions.ContractViolationException;

import com.pwc.insuranceclaims.quickclaim.Claim;
import com.pwc.insuranceclaims.quickclaim.ClaimStatus;
import com.pwc.insuranceclaims.web.action.workflowmgt.ProcessClaimProcessAction;

//@Scope(ScopeType.CONVERSATION)
@Name("claimAction")
public class ClaimAction extends ClaimActionBase implements
		java.io.Serializable {

	@In(create = true)
	ProcessClaimProcessAction processClaimProcessAction;

	@In(scope = ScopeType.BUSINESS_PROCESS, required = false)
	Claim processToken;

	@Override
	public String save() {
		boolean isnew = isNew();
		String ret = super.save();
		if (isnew) {

			processClaimProcessAction.setProcessToken(this.getInstance());
			processClaimProcessAction.startProcess();
		}
		return ret;
	}

	public void updateStatus(String status) {
		if (StringUtils.isEmpty(status))
			throw new ContractViolationException(
					"Recieved empty string for updating status");

		Claim claim = (Claim) ExecutionContext.currentExecutionContext()
				.getVariable("processToken");
		load(claim.getId());

		try {
			getInstance().setStatus(ClaimStatus.valueOf(status));
		} catch (NullPointerException npe) {
			throw new ContractViolationException("Claim is null");
		} catch (Exception e) {
			throw new ContractViolationException(status
					+ " couldnt be cast to an enum literal of type 'Status'");
		}

		save();

	}

}
