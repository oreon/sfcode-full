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

			timeTrackingEntry.setHours(686);

			timeTrackingEntry.setDetails("Mark");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.12.04 03:40:59 EST"));

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

			timeTrackingEntry.setHours(1886);

			timeTrackingEntry.setDetails("Mark");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.16 16:02:37 EDT"));

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

			timeTrackingEntry.setHours(4595);

			timeTrackingEntry.setDetails("epsilon");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.24 17:20:26 EDT"));

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

			timeTrackingEntry.setHours(3843);

			timeTrackingEntry.setDetails("Lavendar");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.10.28 12:07:39 EDT"));

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

			timeTrackingEntry.setHours(7360);

			timeTrackingEntry.setDetails("Lavendar");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.11.14 21:04:52 EST"));

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
