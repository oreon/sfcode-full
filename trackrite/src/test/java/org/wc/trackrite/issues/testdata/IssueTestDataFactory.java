package org.wc.trackrite.issues.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class IssueTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.issues.Issue> {

	private List<org.wc.trackrite.issues.Issue> issues = new ArrayList<org.wc.trackrite.issues.Issue>();

	private static final Logger logger = Logger
			.getLogger(IssueTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.issues.IssueAction issueAction;

	org.wc.trackrite.issues.testdata.ProjectTestDataFactory projectTestDataFactory = new org.wc.trackrite.issues.testdata.ProjectTestDataFactory();

	org.wc.trackrite.domain.testdata.EmployeeTestDataFactory developerTestDataFactory = new org.wc.trackrite.domain.testdata.EmployeeTestDataFactory();

	public void register(org.wc.trackrite.issues.Issue issue) {
		issues.add(issue);
	}

	public org.wc.trackrite.issues.Issue createIssueOne() {
		org.wc.trackrite.issues.Issue issue = new org.wc.trackrite.issues.Issue();

		try {

			issue.setTitle("Eric");

			issue.setDescription("beta");

			issue.setCloseTime(dateFormat.parse("2010.09.28 12:35:45 EDT"));

			issue.setEstimate(4210);

			issue.setProject(projectTestDataFactory.getRandomRecord());

			issue.setDeveloper(developerTestDataFactory.getRandomRecord());

			register(issue);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return issue;
	}

	public org.wc.trackrite.issues.Issue createIssueTwo() {
		org.wc.trackrite.issues.Issue issue = new org.wc.trackrite.issues.Issue();

		try {

			issue.setTitle("delta");

			issue.setDescription("zeta");

			issue.setCloseTime(dateFormat.parse("2010.10.11 13:50:47 EDT"));

			issue.setEstimate(2210);

			issue.setProject(projectTestDataFactory.getRandomRecord());

			issue.setDeveloper(developerTestDataFactory.getRandomRecord());

			register(issue);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return issue;
	}

	public org.wc.trackrite.issues.Issue createIssueThree() {
		org.wc.trackrite.issues.Issue issue = new org.wc.trackrite.issues.Issue();

		try {

			issue.setTitle("alpha");

			issue.setDescription("epsilon");

			issue.setCloseTime(dateFormat.parse("2010.09.23 20:17:59 EDT"));

			issue.setEstimate(5178);

			issue.setProject(projectTestDataFactory.getRandomRecord());

			issue.setDeveloper(developerTestDataFactory.getRandomRecord());

			register(issue);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return issue;
	}

	public org.wc.trackrite.issues.Issue createIssueFour() {
		org.wc.trackrite.issues.Issue issue = new org.wc.trackrite.issues.Issue();

		try {

			issue.setTitle("alpha");

			issue.setDescription("pi");

			issue.setCloseTime(dateFormat.parse("2010.10.17 21:15:12 EDT"));

			issue.setEstimate(3377);

			issue.setProject(projectTestDataFactory.getRandomRecord());

			issue.setDeveloper(developerTestDataFactory.getRandomRecord());

			register(issue);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return issue;
	}

	public org.wc.trackrite.issues.Issue createIssueFive() {
		org.wc.trackrite.issues.Issue issue = new org.wc.trackrite.issues.Issue();

		try {

			issue.setTitle("Wilson");

			issue.setDescription("Malissa");

			issue.setCloseTime(dateFormat.parse("2010.11.08 08:10:47 EST"));

			issue.setEstimate(6981);

			issue.setProject(projectTestDataFactory.getRandomRecord());

			issue.setDeveloper(developerTestDataFactory.getRandomRecord());

			register(issue);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return issue;
	}

	public org.wc.trackrite.issues.Issue getRandomRecord() {

		if (issues.isEmpty()) {
			createAll();
		}

		return issues.get(new Random().nextInt(issues.size()));
	}

	public List<org.wc.trackrite.issues.Issue> createAll() {
		createIssueOne();
		createIssueTwo();
		createIssueThree();
		createIssueFour();
		createIssueFive();

		return issues;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (issueAction == null)
			issueAction = (com.nas.recovery.web.action.issues.IssueAction) Component
					.getInstance("issueAction");

		for (org.wc.trackrite.issues.Issue issue : issues) {
			//try {
			issueAction.setInstance(issue);
			issueAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" Issue " + issue.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new IssueTestDataFactory().persistAll();
	}

}
