package org.wc.trackrite.schedule.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class ScheduleItemTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.schedule.ScheduleItem> {

	private List<org.wc.trackrite.schedule.ScheduleItem> scheduleItems = new ArrayList<org.wc.trackrite.schedule.ScheduleItem>();

	private static final Logger logger = Logger
			.getLogger(ScheduleItemTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.schedule.ScheduleItemAction scheduleItemAction;

	org.wc.trackrite.domain.testdata.EmployeeTestDataFactory employeeTestDataFactory = new org.wc.trackrite.domain.testdata.EmployeeTestDataFactory();

	public void register(org.wc.trackrite.schedule.ScheduleItem scheduleItem) {
		scheduleItems.add(scheduleItem);
	}

	public org.wc.trackrite.schedule.ScheduleItem createScheduleItemOne() {
		org.wc.trackrite.schedule.ScheduleItem scheduleItem = new org.wc.trackrite.schedule.ScheduleItem();

		try {

			scheduleItem.setStartDate(dateFormat
					.parse("2010.10.24 04:14:43 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.02 12:34:43 EDT"));

			scheduleItem.setEmployee(employeeTestDataFactory.getRandomRecord());

			register(scheduleItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return scheduleItem;
	}

	public org.wc.trackrite.schedule.ScheduleItem createScheduleItemTwo() {
		org.wc.trackrite.schedule.ScheduleItem scheduleItem = new org.wc.trackrite.schedule.ScheduleItem();

		try {

			scheduleItem.setStartDate(dateFormat
					.parse("2010.10.13 01:06:22 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.05 12:28:36 EDT"));

			scheduleItem.setEmployee(employeeTestDataFactory.getRandomRecord());

			register(scheduleItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return scheduleItem;
	}

	public org.wc.trackrite.schedule.ScheduleItem createScheduleItemThree() {
		org.wc.trackrite.schedule.ScheduleItem scheduleItem = new org.wc.trackrite.schedule.ScheduleItem();

		try {

			scheduleItem.setStartDate(dateFormat
					.parse("2010.11.23 19:21:56 EST"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.14 19:58:03 EST"));

			scheduleItem.setEmployee(employeeTestDataFactory.getRandomRecord());

			register(scheduleItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return scheduleItem;
	}

	public org.wc.trackrite.schedule.ScheduleItem createScheduleItemFour() {
		org.wc.trackrite.schedule.ScheduleItem scheduleItem = new org.wc.trackrite.schedule.ScheduleItem();

		try {

			scheduleItem.setStartDate(dateFormat
					.parse("2010.11.02 22:39:09 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.06 10:36:22 EDT"));

			scheduleItem.setEmployee(employeeTestDataFactory.getRandomRecord());

			register(scheduleItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return scheduleItem;
	}

	public org.wc.trackrite.schedule.ScheduleItem createScheduleItemFive() {
		org.wc.trackrite.schedule.ScheduleItem scheduleItem = new org.wc.trackrite.schedule.ScheduleItem();

		try {

			scheduleItem.setStartDate(dateFormat
					.parse("2010.10.31 06:21:56 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.09 03:28:03 EST"));

			scheduleItem.setEmployee(employeeTestDataFactory.getRandomRecord());

			register(scheduleItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return scheduleItem;
	}

	public List<org.wc.trackrite.schedule.ScheduleItem> createAll() {
		createScheduleItemOne();
		createScheduleItemTwo();
		createScheduleItemThree();
		createScheduleItemFour();
		createScheduleItemFive();

		return scheduleItems;
	}

	@Override
	public List<org.wc.trackrite.schedule.ScheduleItem> getListOfRecords() {
		return scheduleItems;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.schedule.ScheduleItem e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.schedule.ScheduleItem scheduleItem : scheduleItems) {
			persist(scheduleItem);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ScheduleItemTestDataFactory().persistAll();
	}

}
