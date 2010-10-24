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

public class ScheduleTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.schedule.Schedule> {

	private List<org.wc.trackrite.schedule.Schedule> schedules = new ArrayList<org.wc.trackrite.schedule.Schedule>();

	private static final Logger logger = Logger
			.getLogger(ScheduleTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.schedule.ScheduleAction scheduleAction;

	org.wc.trackrite.issues.testdata.ProjectTestDataFactory projectTestDataFactory = new org.wc.trackrite.issues.testdata.ProjectTestDataFactory();

	public void register(org.wc.trackrite.schedule.Schedule schedule) {
		schedules.add(schedule);
	}

	public org.wc.trackrite.schedule.Schedule createScheduleOne() {
		org.wc.trackrite.schedule.Schedule schedule = new org.wc.trackrite.schedule.Schedule();

		try {

			schedule.setName("beta");

			schedule.setProject(projectTestDataFactory.getRandomRecord());

			register(schedule);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return schedule;
	}

	public org.wc.trackrite.schedule.Schedule createScheduleTwo() {
		org.wc.trackrite.schedule.Schedule schedule = new org.wc.trackrite.schedule.Schedule();

		try {

			schedule.setName("delta");

			schedule.setProject(projectTestDataFactory.getRandomRecord());

			register(schedule);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return schedule;
	}

	public org.wc.trackrite.schedule.Schedule createScheduleThree() {
		org.wc.trackrite.schedule.Schedule schedule = new org.wc.trackrite.schedule.Schedule();

		try {

			schedule.setName("pi");

			schedule.setProject(projectTestDataFactory.getRandomRecord());

			register(schedule);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return schedule;
	}

	public org.wc.trackrite.schedule.Schedule createScheduleFour() {
		org.wc.trackrite.schedule.Schedule schedule = new org.wc.trackrite.schedule.Schedule();

		try {

			schedule.setName("alpha");

			schedule.setProject(projectTestDataFactory.getRandomRecord());

			register(schedule);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return schedule;
	}

	public org.wc.trackrite.schedule.Schedule createScheduleFive() {
		org.wc.trackrite.schedule.Schedule schedule = new org.wc.trackrite.schedule.Schedule();

		try {

			schedule.setName("Malissa");

			schedule.setProject(projectTestDataFactory.getRandomRecord());

			register(schedule);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return schedule;
	}

	public org.wc.trackrite.schedule.Schedule getRandomRecord() {

		if (schedules.isEmpty()) {
			createAll();
		}

		return schedules.get(new Random().nextInt(schedules.size()));
	}

	public List<org.wc.trackrite.schedule.Schedule> createAll() {
		createScheduleOne();
		createScheduleTwo();
		createScheduleThree();
		createScheduleFour();
		createScheduleFive();

		return schedules;
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.schedule.Schedule schedule : schedules) {
			persist(schedule);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ScheduleTestDataFactory().persistAll();
	}

}
