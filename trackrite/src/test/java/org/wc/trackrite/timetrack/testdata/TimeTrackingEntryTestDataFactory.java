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

			timeTrackingEntry.setHours(4356);

			timeTrackingEntry.setDetails("Mark");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.12.16 07:56:05 EST"));

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

			timeTrackingEntry.setHours(9583);

			timeTrackingEntry.setDetails("zeta");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.12.01 09:15:33 EST"));

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

			timeTrackingEntry.setHours(2847);

			timeTrackingEntry.setDetails("delta");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.12.21 22:52:13 EST"));

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

			timeTrackingEntry.setHours(907);

			timeTrackingEntry.setDetails("zeta");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.12.24 04:31:07 EST"));

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

			timeTrackingEntry.setHours(7055);

			timeTrackingEntry.setDetails("Lavendar");

			timeTrackingEntry.setDate(dateFormat
					.parse("2010.12.25 05:14:27 EST"));

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
