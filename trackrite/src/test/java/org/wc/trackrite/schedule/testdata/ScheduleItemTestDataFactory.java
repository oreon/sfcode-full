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

	org.wc.trackrite.schedule.testdata.DetailItemTestDataFactory detailItemTestDataFactory = new org.wc.trackrite.schedule.testdata.DetailItemTestDataFactory();

	public void register(org.wc.trackrite.schedule.ScheduleItem scheduleItem) {
		scheduleItems.add(scheduleItem);
	}

	public org.wc.trackrite.schedule.ScheduleItem createScheduleItemOne() {
		org.wc.trackrite.schedule.ScheduleItem scheduleItem = new org.wc.trackrite.schedule.ScheduleItem();

		try {

			scheduleItem.setStartDate(dateFormat
					.parse("2010.10.15 03:25:45 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.03 04:47:56 EDT"));

			scheduleItem.setDetailItem(detailItemTestDataFactory
					.getRandomRecord());

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
					.parse("2010.11.08 13:10:10 EST"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.08 05:35:45 EDT"));

			scheduleItem.setDetailItem(detailItemTestDataFactory
					.getRandomRecord());

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
					.parse("2010.10.31 12:09:05 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.10.26 12:29:05 EDT"));

			scheduleItem.setDetailItem(detailItemTestDataFactory
					.getRandomRecord());

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
					.parse("2010.10.28 00:20:43 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.07 07:15:45 EST"));

			scheduleItem.setDetailItem(detailItemTestDataFactory
					.getRandomRecord());

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
					.parse("2010.10.10 21:44:03 EDT"));

			scheduleItem
					.setEndDate(dateFormat.parse("2010.11.09 04:32:57 EST"));

			scheduleItem.setDetailItem(detailItemTestDataFactory
					.getRandomRecord());

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
