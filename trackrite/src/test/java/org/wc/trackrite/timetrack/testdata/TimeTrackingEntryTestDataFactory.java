package org.wc.trackrite.timetrack.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class TimeTrackingEntryTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.timetrack.TimeTrackingEntry> {

	private List<org.wc.trackrite.timetrack.TimeTrackingEntry> timeTrackingEntrys = new ArrayList<org.wc.trackrite.timetrack.TimeTrackingEntry>();

	private static final Logger logger = Logger
			.getLogger(TimeTrackingEntryTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.timetrack.TimeTrackingEntryAction timeTrackingEntryAction;

	org.wc.trackrite.issues.testdata.IssueTestDataFactory issueTestDataFactory = new org.wc.trackrite.issues.testdata.IssueTestDataFactory();

	org.wc.trackrite.timetrack.testdata.WorkDayTestDataFactory workDayTestDataFactory = new org.wc.trackrite.timetrack.testdata.WorkDayTestDataFactory();

	public void register(
			org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry) {
		timeTrackingEntrys.add(timeTrackingEntry);
	}

	public org.wc.trackrite.timetrack.TimeTrackingEntry createTimeTrackingEntryOne() {
		org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry = new org.wc.trackrite.timetrack.TimeTrackingEntry();

		try {

			timeTrackingEntry.setHours(3712);

			timeTrackingEntry.setDetails("delta");

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setWorkDay(workDayTestDataFactory
					.getRandomRecord());

			register(timeTrackingEntry);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeTrackingEntry;
	}

	public org.wc.trackrite.timetrack.TimeTrackingEntry createTimeTrackingEntryTwo() {
		org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry = new org.wc.trackrite.timetrack.TimeTrackingEntry();

		try {

			timeTrackingEntry.setHours(4780);

			timeTrackingEntry.setDetails("gamma");

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setWorkDay(workDayTestDataFactory
					.getRandomRecord());

			register(timeTrackingEntry);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeTrackingEntry;
	}

	public org.wc.trackrite.timetrack.TimeTrackingEntry createTimeTrackingEntryThree() {
		org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry = new org.wc.trackrite.timetrack.TimeTrackingEntry();

		try {

			timeTrackingEntry.setHours(8348);

			timeTrackingEntry.setDetails("delta");

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setWorkDay(workDayTestDataFactory
					.getRandomRecord());

			register(timeTrackingEntry);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeTrackingEntry;
	}

	public org.wc.trackrite.timetrack.TimeTrackingEntry createTimeTrackingEntryFour() {
		org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry = new org.wc.trackrite.timetrack.TimeTrackingEntry();

		try {

			timeTrackingEntry.setHours(6012);

			timeTrackingEntry.setDetails("gamma");

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setWorkDay(workDayTestDataFactory
					.getRandomRecord());

			register(timeTrackingEntry);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeTrackingEntry;
	}

	public org.wc.trackrite.timetrack.TimeTrackingEntry createTimeTrackingEntryFive() {
		org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry = new org.wc.trackrite.timetrack.TimeTrackingEntry();

		try {

			timeTrackingEntry.setHours(2693);

			timeTrackingEntry.setDetails("alpha");

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setWorkDay(workDayTestDataFactory
					.getRandomRecord());

			register(timeTrackingEntry);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeTrackingEntry;
	}

	public org.wc.trackrite.timetrack.TimeTrackingEntry getRandomRecord() {

		if (timeTrackingEntrys.isEmpty()) {
			createAll();
		}

		return timeTrackingEntrys.get(new Random().nextInt(timeTrackingEntrys
				.size()));
	}

	public List<org.wc.trackrite.timetrack.TimeTrackingEntry> createAll() {
		createTimeTrackingEntryOne();
		createTimeTrackingEntryTwo();
		createTimeTrackingEntryThree();
		createTimeTrackingEntryFour();
		createTimeTrackingEntryFive();

		return timeTrackingEntrys;
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry : timeTrackingEntrys) {
			persist(timeTrackingEntry);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new TimeTrackingEntryTestDataFactory().persistAll();
	}

}
