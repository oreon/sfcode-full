package com.nas.recovery.web.action.workflows;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ExecutionContext;

import com.nas.recovery.web.action.common.RecoveryToken;
import com.nas.recovery.web.action.realestate.RealEstatePropertyAction;

@Name("albertaRecoveryProcessAction")
//@Scope(ScopeType.CONVERSATION)
public class AlbertaRecovery extends AlbertaRecoveryBase {
	
	@In(create = true, value = "realEstatePropertyAction")
	RealEstatePropertyAction realEstatePropertyAction;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	RecoveryToken recoveryToken = new RecoveryToken();
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String desc ;
	
	
	private String lawyer;
	
	
	public RecoveryToken getRecoveryToken() {
		return recoveryToken;
	}


	public void setRecoveryToken(RecoveryToken recoveryToken) {
		this.recoveryToken = recoveryToken;
	}


	public String getLawyer() {
		return lawyer;
	}


	public void setLawyer(String lawyer) {
		this.lawyer = lawyer;
	}
	
	


	
	
	@CreateProcess(definition = "albertaRecovery")
	public void startProcess() {
		recoveryToken.setLawyer( lawyer );//leaveRequest.getTo();
		recoveryToken.setPropertyManager("jimmy");
		recoveryToken.setLenderContact( identity.getCredentials().getUsername() ); 
		recoveryToken.setRealEstateProperty(realEstatePropertyAction.getInstance());
		desc = realEstatePropertyAction.getInstance().getDisplayName();
	}
	
	

}
