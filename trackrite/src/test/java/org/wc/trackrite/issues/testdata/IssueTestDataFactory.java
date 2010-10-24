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

			issue.setTitle("Mark");

			issue.setDescription("John");

			issue.setCloseTime(dateFormat.parse("2010.10.21 00:07:33 EDT"));

			issue.setEstimate(7720);

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

			issue.setTitle("pi");

			issue.setDescription("theta");

			issue.setCloseTime(dateFormat.parse("2010.10.13 16:01:25 EDT"));

			issue.setEstimate(9869);

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

			issue.setTitle("Wilson");

			issue.setDescription("Malissa");

			issue.setCloseTime(dateFormat.parse("2010.11.13 15:14:13 EST"));

			issue.setEstimate(7116);

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

			issue.setTitle("Lavendar");

			issue.setDescription("zeta");

			issue.setCloseTime(dateFormat.parse("2010.10.06 16:27:00 EDT"));

			issue.setEstimate(8305);

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

			issue.setTitle("Eric");

			issue.setDescription("Lavendar");

			issue.setCloseTime(dateFormat.parse("2010.10.09 17:24:13 EDT"));

			issue.setEstimate(5756);

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
		init();
		createAll();

		for (org.wc.trackrite.issues.Issue issue : issues) {
			persist(issue);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new IssueTestDataFactory().persistAll();
	}

}
