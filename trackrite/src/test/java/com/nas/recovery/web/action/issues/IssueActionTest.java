package com.nas.recovery.web.action.issues;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.mock.AbstractSeamTest.ComponentTest;
import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.issues.Issue;
import org.wc.trackrite.issues.Project;

public class IssueActionTest extends IssueActionTestBase {
	
	@Test
	public void testIssue() throws Exception{
		
		
		new ComponentTest() {

			protected void testComponents() throws Exception {
				issueAction = (IssueAction) Component.getInstance("issueAction");
				Issue issue = new Issue();
				issue.setTitle("test issue");
				issue.setProject(issueAction.getEntityManager().find(Project.class, 1L));
				issueAction.setInstance(issue);
				issueAction.save();
			}
		}.run();
	}


}