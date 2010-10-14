package com.nas.recovery.web.action.issues;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.wc.trackrite.issues.Issue;
import org.wc.trackrite.issues.Status;
import org.jboss.seam.bpm.ProcessInstance;

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
			getInstance().setProcessId(ProcessInstance.instance().getId());
		}
		
		super.save();
		return ret;
	}

	private void launchProcess() {
		bugManagement.setToken(getInstance());
		bugManagement.startProcess();
	}
	
	public void updateStatus(String status){
		load(token.getId());
		getInstance().setStatus(Status.valueOf(status));
		save();
	}
	
	
	public void updateStatusEnum(Status status){
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
