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

public class WorkDayTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.timetrack.WorkDay> {

	private List<org.wc.trackrite.timetrack.WorkDay> workDays = new ArrayList<org.wc.trackrite.timetrack.WorkDay>();

	private static final Logger logger = Logger
			.getLogger(WorkDayTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.timetrack.WorkDayAction workDayAction;

	public void register(org.wc.trackrite.timetrack.WorkDay workDay) {
		workDays.add(workDay);
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayOne() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.11.23 12:55:53 EST"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayTwo() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.11.16 09:04:11 EST"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayThree() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.10.12 13:25:16 EDT"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayFour() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.11.14 13:33:38 EST"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayFive() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.11.18 19:52:33 EST"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public List<org.wc.trackrite.timetrack.WorkDay> createAll() {
		createWorkDayOne();
		createWorkDayTwo();
		createWorkDayThree();
		createWorkDayFour();
		createWorkDayFive();

		return workDays;
	}

	@Override
	public List<org.wc.trackrite.timetrack.WorkDay> getListOfRecords() {
		return workDays;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.timetrack.WorkDay e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.timetrack.WorkDay workDay : workDays) {
			persist(workDay);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new WorkDayTestDataFactory().persistAll();
	}

}
