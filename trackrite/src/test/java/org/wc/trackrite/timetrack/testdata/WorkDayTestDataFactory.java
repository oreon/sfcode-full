package org.wc.trackrite.timetrack.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.seam.action.AbstractTestDataFactory; //import org.witchcraft.model.support.testing.AbstractTestDataFactory;
//import org.witchcraft.model.support.testing.TestDataFactory;
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

			workDay.setDate(dateFormat.parse("2010.09.23 17:15:50 EDT"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayTwo() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.10.19 03:57:28 EDT"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayThree() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.10.09 21:13:03 EDT"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayFour() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.10.14 09:23:03 EDT"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay createWorkDayFive() {
		org.wc.trackrite.timetrack.WorkDay workDay = new org.wc.trackrite.timetrack.WorkDay();

		try {

			workDay.setDate(dateFormat.parse("2010.09.20 14:02:30 EDT"));

			register(workDay);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return workDay;
	}

	public org.wc.trackrite.timetrack.WorkDay getRandomRecord() {

		if (workDays.isEmpty()) {
			createAll();
		}

		return workDays.get(new Random().nextInt(workDays.size()));
	}

	public List<org.wc.trackrite.timetrack.WorkDay> createAll() {
		createWorkDayOne();
		createWorkDayTwo();
		createWorkDayThree();
		createWorkDayFour();
		createWorkDayFive();

		return workDays;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (workDayAction == null)
			workDayAction = (com.nas.recovery.web.action.timetrack.WorkDayAction) Component
					.getInstance("workDayAction");

		for (org.wc.trackrite.timetrack.WorkDay workDay : workDays) {
			//try {
			workDayAction.setInstance(workDay);
			workDayAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" WorkDay " + workDay.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new WorkDayTestDataFactory().persistAll();
	}

}
