package com.nas.recovery.web.action.workflows;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.web.ServletContexts;
import org.wc.trackrite.issues.Issue;
import org.wc.trackrite.issues.Status;

import com.nas.recovery.web.action.issues.IssueAction;

@Name("bugManagementProcessAction")
// @Scope(ScopeType.CONVERSATION)
public class BugManagement extends BugManagementBase {
	
	@In(create = true, value = "issueAction")
	IssueAction issueAction;
	
	
	
	//@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	//IssueToken issueToken = new IssueToken();
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String initiator ;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String developer ;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String qa ;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	Long issueId ;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String title;
	
	
	
	@CreateProcess(definition = "bugManagement" , processKey= "#{issueAction.instance.id}")
	public void startProcess() {
		// TODO Auto-generated method stub
		initiator = identity.getCredentials().getUsername();
		issueAction.save();
		issueId = issueAction.getInstance().getId();
		title = issueAction.getInstance().getTitle();
		statusMessages.add("Process Started");
		//super.startProcess();
	}
	
	@Override
	protected void loadInstance() {
		//Long issueIdTemp = (Long)task.getVariable("issueId");
		//issueAction.loadFromId(issueIdTemp);
		//super.loadInstance();
	}
	
	
	
	@Override
	@EndTask(transition = "assign")
	public void assignAssignDeveloperTask() {
		//issueToken.getIssue().setEmployee("tammy");
	}
	
	@EndTask(transition = "accept")
	public void acceptReviewIssueTask() {
		developer = identity.getCredentials().getUsername();
		updateIssue(Status.Started);
	}
	
	@EndTask(transition = "requestModification")
	public void requestModificationVerifyfixTask() {
		updateIssue(Status.Started);
		
	}
	
	@EndTask(transition = "reject")
	public void rejectReviewIssueTask() {
		updateIssue(Status.Unassigned);
	}



	@EndTask(transition = "close")
	public void closeVerifyfixTask() {
		updateIssue(Status.Closed);
	}
	
	@EndTask(transition = "fixed")
	public void fixedWorkOnIssueTask() {
		updateIssue(Status.ReadyForQA);
	}

	private void updateIssue(Status status) {
		issueAction.load((Long)task.getVariable("issueId"));
		issueAction.getInstance().setStatus(status);
		issueAction.save();
	}
	
	
	

}
