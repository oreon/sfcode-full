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
					.parse("2010.11.13 08:54:52 EST"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.28 05:44:52 EST"));

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
					.parse("2010.10.27 17:44:52 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.07 03:41:32 EST"));

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
					.parse("2010.11.07 12:44:19 EST"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.17 02:04:52 EDT"));

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
					.parse("2010.11.25 18:15:25 EST"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.17 15:44:19 EST"));

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
					.parse("2010.10.16 17:47:07 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.28 12:15:57 EDT"));

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
