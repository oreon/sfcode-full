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
					.parse("2010.11.01 00:48:06 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.23 19:00:21 EDT"));

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
					.parse("2010.10.06 03:31:26 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.16 15:40:21 EDT"));

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
					.parse("2010.11.10 20:41:26 EST"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.17 02:03:41 EDT"));

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
					.parse("2010.10.14 18:50:21 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.25 02:17:01 EDT"));

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
					.parse("2010.09.30 04:47:34 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.10 07:20:21 EST"));

			scheduleItem.setEmployee(employeeTestDataFactory.getRandomRecord());

			register(scheduleItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return scheduleItem;
	}

	public org.wc.trackrite.schedule.ScheduleItem getRandomRecord() {

		if (scheduleItems.isEmpty()) {
			createAll();
		}

		return scheduleItems.get(new Random().nextInt(scheduleItems.size()));
	}

	public List<org.wc.trackrite.schedule.ScheduleItem> createAll() {
		createScheduleItemOne();
		createScheduleItemTwo();
		createScheduleItemThree();
		createScheduleItemFour();
		createScheduleItemFive();

		return scheduleItems;
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
