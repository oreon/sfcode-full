package com.nas.recovery.web.action.workflows;

import java.io.Serializable;

import org.wc.trackrite.domain.Employee;
import org.wc.trackrite.issues.Issue;

public class IssueToken implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6402906016586025313L;

	Employee initiator;
	
	Employee developer;
	
	Employee qa;
	
	Issue issue;

	public Employee getInitiator() {
		return initiator;
	}

	public void setInitiator(Employee initiator) {
		this.initiator = initiator;
	}

	public Employee getDeveloper() {
		return developer;
	}

	public void setDeveloper(Employee developer) {
		this.developer = developer;
	}

	public Employee getQa() {
		return qa;
	}

	public void setQa(Employee qa) {
		this.qa = qa;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

}
