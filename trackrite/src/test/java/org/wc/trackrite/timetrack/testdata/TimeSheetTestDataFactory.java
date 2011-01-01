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

public class TimeSheetTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.timetrack.TimeSheet> {

	private List<org.wc.trackrite.timetrack.TimeSheet> timeSheets = new ArrayList<org.wc.trackrite.timetrack.TimeSheet>();

	private static final Logger logger = Logger
			.getLogger(TimeSheetTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.timetrack.TimeSheetAction timeSheetAction;

	public void register(org.wc.trackrite.timetrack.TimeSheet timeSheet) {
		timeSheets.add(timeSheet);
	}

	public org.wc.trackrite.timetrack.TimeSheet createTimeSheetOne() {
		org.wc.trackrite.timetrack.TimeSheet timeSheet = new org.wc.trackrite.timetrack.TimeSheet();

		try {

			timeSheet.setTitle("Mark");

			register(timeSheet);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeSheet;
	}

	public org.wc.trackrite.timetrack.TimeSheet createTimeSheetTwo() {
		org.wc.trackrite.timetrack.TimeSheet timeSheet = new org.wc.trackrite.timetrack.TimeSheet();

		try {

			timeSheet.setTitle("gamma");

			register(timeSheet);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeSheet;
	}

	public org.wc.trackrite.timetrack.TimeSheet createTimeSheetThree() {
		org.wc.trackrite.timetrack.TimeSheet timeSheet = new org.wc.trackrite.timetrack.TimeSheet();

		try {

			timeSheet.setTitle("Wilson");

			register(timeSheet);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeSheet;
	}

	public org.wc.trackrite.timetrack.TimeSheet createTimeSheetFour() {
		org.wc.trackrite.timetrack.TimeSheet timeSheet = new org.wc.trackrite.timetrack.TimeSheet();

		try {

			timeSheet.setTitle("Mark");

			register(timeSheet);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeSheet;
	}

	public org.wc.trackrite.timetrack.TimeSheet createTimeSheetFive() {
		org.wc.trackrite.timetrack.TimeSheet timeSheet = new org.wc.trackrite.timetrack.TimeSheet();

		try {

			timeSheet.setTitle("gamma");

			register(timeSheet);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return timeSheet;
	}

	public List<org.wc.trackrite.timetrack.TimeSheet> createAll() {
		createTimeSheetOne();
		createTimeSheetTwo();
		createTimeSheetThree();
		createTimeSheetFour();
		createTimeSheetFive();

		return timeSheets;
	}

	@Override
	public List<org.wc.trackrite.timetrack.TimeSheet> getListOfRecords() {
		return timeSheets;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.timetrack.TimeSheet e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.timetrack.TimeSheet timeSheet : timeSheets) {
			persist(timeSheet);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new TimeSheetTestDataFactory().persistAll();
	}

}
