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

			issue.setTitle("delta");

			issue.setDescription("epsilon");

			issue.setCloseTime(dateFormat.parse("2010.12.03 15:14:19 EST"));

			issue.setEstimate(9050);

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

			issue.setTitle("Eric");

			issue.setDescription("Mark");

			issue.setCloseTime(dateFormat.parse("2010.10.27 13:54:19 EDT"));

			issue.setEstimate(5088);

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

			issue.setTitle("theta");

			issue.setDescription("gamma");

			issue.setCloseTime(dateFormat.parse("2010.10.20 13:43:46 EDT"));

			issue.setEstimate(827);

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

			issue.setTitle("John");

			issue.setDescription("epsilon");

			issue.setCloseTime(dateFormat.parse("2010.11.11 10:18:12 EST"));

			issue.setEstimate(9271);

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

			issue.setDescription("John");

			issue.setCloseTime(dateFormat.parse("2010.11.22 06:48:44 EST"));

			issue.setEstimate(5440);

			issue.setProject(projectTestDataFactory.getRandomRecord());

			issue.setDeveloper(developerTestDataFactory.getRandomRecord());

			register(issue);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return issue;
	}

	public List<org.wc.trackrite.issues.Issue> createAll() {
		createIssueOne();
		createIssueTwo();
		createIssueThree();
		createIssueFour();
		createIssueFive();

		return issues;
	}

	@Override
	public List<org.wc.trackrite.issues.Issue> getListOfRecords() {
		return issues;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.issues.Issue e ";
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
