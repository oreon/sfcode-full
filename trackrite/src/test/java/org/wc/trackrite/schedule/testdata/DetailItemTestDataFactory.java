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

public class DetailItemTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.schedule.DetailItem> {

	private List<org.wc.trackrite.schedule.DetailItem> detailItems = new ArrayList<org.wc.trackrite.schedule.DetailItem>();

	private static final Logger logger = Logger
			.getLogger(DetailItemTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.schedule.DetailItemAction detailItemAction;

	org.wc.trackrite.schedule.testdata.ScheduleItemTestDataFactory scheduleItemTestDataFactory = new org.wc.trackrite.schedule.testdata.ScheduleItemTestDataFactory();

	public void register(org.wc.trackrite.schedule.DetailItem detailItem) {
		detailItems.add(detailItem);
	}

	public org.wc.trackrite.schedule.DetailItem createDetailItemOne() {
		org.wc.trackrite.schedule.DetailItem detailItem = new org.wc.trackrite.schedule.DetailItem();

		try {

			detailItem.setScheduleItem(scheduleItemTestDataFactory
					.getRandomRecord());

			register(detailItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return detailItem;
	}

	public org.wc.trackrite.schedule.DetailItem createDetailItemTwo() {
		org.wc.trackrite.schedule.DetailItem detailItem = new org.wc.trackrite.schedule.DetailItem();

		try {

			detailItem.setScheduleItem(scheduleItemTestDataFactory
					.getRandomRecord());

			register(detailItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return detailItem;
	}

	public org.wc.trackrite.schedule.DetailItem createDetailItemThree() {
		org.wc.trackrite.schedule.DetailItem detailItem = new org.wc.trackrite.schedule.DetailItem();

		try {

			detailItem.setScheduleItem(scheduleItemTestDataFactory
					.getRandomRecord());

			register(detailItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return detailItem;
	}

	public org.wc.trackrite.schedule.DetailItem createDetailItemFour() {
		org.wc.trackrite.schedule.DetailItem detailItem = new org.wc.trackrite.schedule.DetailItem();

		try {

			detailItem.setScheduleItem(scheduleItemTestDataFactory
					.getRandomRecord());

			register(detailItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return detailItem;
	}

	public org.wc.trackrite.schedule.DetailItem createDetailItemFive() {
		org.wc.trackrite.schedule.DetailItem detailItem = new org.wc.trackrite.schedule.DetailItem();

		try {

			detailItem.setScheduleItem(scheduleItemTestDataFactory
					.getRandomRecord());

			register(detailItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return detailItem;
	}

	public org.wc.trackrite.schedule.DetailItem getRandomRecord() {

		if (detailItems.isEmpty()) {
			createAll();
		}

		return detailItems.get(new Random().nextInt(detailItems.size()));
	}

	public List<org.wc.trackrite.schedule.DetailItem> createAll() {
		createDetailItemOne();
		createDetailItemTwo();
		createDetailItemThree();
		createDetailItemFour();
		createDetailItemFive();

		return detailItems;
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.schedule.DetailItem detailItem : detailItems) {
			persist(detailItem);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new DetailItemTestDataFactory().persistAll();
	}

}
