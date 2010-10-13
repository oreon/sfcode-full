package com.nas.recovery.web.action.issues;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.wc.trackrite.issues.Issue;
import org.wc.trackrite.issues.Status;

import com.nas.recovery.web.action.workflowmgt.BugManagement;

//@Scope(ScopeType.CONVERSATION)
@Name("issueAction")
public class IssueAction extends IssueActionBase implements
		java.io.Serializable {
	
	@In(scope=ScopeType.BUSINESS_PROCESS, required=false) 
    Issue token;
	
	@In(create = true, value = "bugManagementProcessAction")
	BugManagement bugManagement;

	@Override
	public String save() {
		boolean isNew = isNew();
		
		String ret = super.save();
		if (isNew) {
			launchProcess();
			getInstance().setProcessId(org.jboss.seam.bpm.ProcessInstance.instance().getId());
		}
		
		super.save();
		return ret;
	}

	private void launchProcess() {
		bugManagement.setToken(getInstance());
		bugManagement.startProcess();
	}
	
	public void updateStatusStr(String status){
		//status.
		//load(token.getId());
		System.out.println("setting status to " + status);
		getInstance().setStatus(Status.valueOf(status));
		save();
	}
	
	
	public void updateStatus(Status status){
		//status.
		//load(token.getId());
		getInstance().setStatus(status);
		save();
	}
	
	public static void main(String[] args) {
		Status status = Status.valueOf("Started");
		System.out.println(status);
	}

}
