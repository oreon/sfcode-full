package com.nas.recovery.web.action.workflows;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.contexts.BusinessProcessContext;
import org.jboss.seam.international.StatusMessage.Severity;
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
	
	
	private String lawyer = "ericl";
	
	private String masterAgent = "john";
	
	private String propertyManager = "jimmy";
	
	public String getMasterAgent() {
		return masterAgent;
	}


	public void setMasterAgent(String masterAgent) {
		this.masterAgent = masterAgent;
	}


	public String getPropertyManager() {
		return propertyManager;
	}


	public void setPropertyManager(String propertyManager) {
		this.propertyManager = propertyManager;
	}






	
	
	
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
	
	


	
	
	@CreateProcess(definition = "albertaRecovery") //,  processKey= "#{issueAction.instance.id}")
	public String startProcess() {
		recoveryToken.setLawyer( lawyer );//leaveRequest.getTo();
		recoveryToken.setPropertyManager(propertyManager);
		recoveryToken.setMasterAgent(masterAgent);
		recoveryToken.setLenderContact( identity.getCredentials().getUsername() ); 
		
		//BusinessProcessContext bpc = BusinessProcessContext.
		
		realEstatePropertyAction.getInstance().setProcessId(4); //businessProcess.getProcessId());
		realEstatePropertyAction.getInstance().setProcessName("albertaRecovery");
		realEstatePropertyAction.save();
		
		recoveryToken.setRealEstateProperty(realEstatePropertyAction.getInstance());
		desc = realEstatePropertyAction.getInstance().getDisplayName();
		
		statusMessages.add(Severity.INFO, "Process Started for {0}", desc);
		return "success" ;
	}
	
	

}
