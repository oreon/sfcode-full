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
					.parse("2010.11.10 08:13:06 EST"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.06 15:34:11 EDT"));

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
					.parse("2010.10.19 03:53:39 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.11 01:07:31 EDT"));

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
					.parse("2010.10.29 10:57:31 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.15 13:15:17 EDT"));

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
					.parse("2010.10.29 06:24:11 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.08 11:54:44 EST"));

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
					.parse("2010.11.06 18:09:46 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.21 17:58:04 EDT"));

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
