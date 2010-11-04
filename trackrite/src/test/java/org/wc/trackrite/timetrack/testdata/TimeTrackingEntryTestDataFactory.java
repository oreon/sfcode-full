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

	org.wc.trackrite.timetrack.testdata.TimeSheetTestDataFactory timeSheetTestDataFactory = new org.wc.trackrite.timetrack.testdata.TimeSheetTestDataFactory();

	public void register(
			org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry) {
		timeTrackingEntrys.add(timeTrackingEntry);
	}

	public org.wc.trackrite.timetrack.TimeTrackingEntry createTimeTrackingEntryOne() {
		org.wc.trackrite.timetrack.TimeTrackingEntry timeTrackingEntry = new org.wc.trackrite.timetrack.TimeTrackingEntry();

		try {

			timeTrackingEntry.setHours(3520);

			timeTrackingEntry.setDetails("pi");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.23 09:21:22 EDT"));

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setTimeSheet(timeSheetTestDataFactory
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

			timeTrackingEntry.setHours(8770);

			timeTrackingEntry.setDetails("Mark");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.18 00:53:33 EDT"));

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setTimeSheet(timeSheetTestDataFactory
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

			timeTrackingEntry.setHours(9913);

			timeTrackingEntry.setDetails("alpha");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.31 11:39:08 EDT"));

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setTimeSheet(timeSheetTestDataFactory
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

			timeTrackingEntry.setHours(2335);

			timeTrackingEntry.setDetails("Malissa");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.14 14:23:33 EDT"));

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setTimeSheet(timeSheetTestDataFactory
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

			timeTrackingEntry.setHours(444);

			timeTrackingEntry.setDetails("alpha");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.31 21:08:35 EDT"));

			timeTrackingEntry.setIssue(issueTestDataFactory.getRandomRecord());

			timeTrackingEntry.setTimeSheet(timeSheetTestDataFactory
					.getRandomRecord());

			register(timeTrackingEntry);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeTrackingEntry;
	}

	public List<org.wc.trackrite.timetrack.TimeTrackingEntry> createAll() {
		createTimeTrackingEntryOne();
		createTimeTrackingEntryTwo();
		createTimeTrackingEntryThree();
		createTimeTrackingEntryFour();
		createTimeTrackingEntryFive();

		return timeTrackingEntrys;
	}

	@Override
	public List<org.wc.trackrite.timetrack.TimeTrackingEntry> getListOfRecords() {
		return timeTrackingEntrys;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.timetrack.TimeTrackingEntry e ";
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
