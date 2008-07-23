package com.cc.civlit.domain.courtdivisions;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.cc.civlit.domain.service.LevelOfCourtService;

@Transactional
public class LevelOfCourtTestDataFactory
		extends
			AbstractTestDataFactory<LevelOfCourt> {

	private List<LevelOfCourt> levelOfCourts = new ArrayList<LevelOfCourt>();

	private static final Logger logger = Logger
			.getLogger(LevelOfCourtTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	LevelOfCourtService levelOfCourtService;

	public LevelOfCourtService getLevelOfCourtService() {
		return levelOfCourtService;
	}

	public void setLevelOfCourtService(LevelOfCourtService levelOfCourtService) {
		this.levelOfCourtService = levelOfCourtService;
	}

	public void register(LevelOfCourt levelOfCourt) {
		levelOfCourts.add(levelOfCourt);
	}

	public LevelOfCourt createLevelOfCourtOne() {
		LevelOfCourt levelOfCourt = new LevelOfCourt();

		try {

			levelOfCourt.setName("epsilon");

			TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("jurisdictionTestDataFactory");

			levelOfCourt
					.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
							.loadOneRecord());

			register(levelOfCourt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return levelOfCourt;
	}

	public LevelOfCourt createLevelOfCourtTwo() {
		LevelOfCourt levelOfCourt = new LevelOfCourt();

		try {

			levelOfCourt.setName("Mark");

			TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("jurisdictionTestDataFactory");

			levelOfCourt
					.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
							.loadOneRecord());

			register(levelOfCourt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return levelOfCourt;
	}

	public LevelOfCourt createLevelOfCourtThree() {
		LevelOfCourt levelOfCourt = new LevelOfCourt();

		try {

			levelOfCourt.setName("Wilson");

			TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("jurisdictionTestDataFactory");

			levelOfCourt
					.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
							.loadOneRecord());

			register(levelOfCourt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return levelOfCourt;
	}

	public LevelOfCourt createLevelOfCourtFour() {
		LevelOfCourt levelOfCourt = new LevelOfCourt();

		try {

			levelOfCourt.setName("Mark");

			TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("jurisdictionTestDataFactory");

			levelOfCourt
					.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
							.loadOneRecord());

			register(levelOfCourt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return levelOfCourt;
	}

	public LevelOfCourt createLevelOfCourtFive() {
		LevelOfCourt levelOfCourt = new LevelOfCourt();

		try {

			levelOfCourt.setName("alpha");

			TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("jurisdictionTestDataFactory");

			levelOfCourt
					.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
							.loadOneRecord());

			register(levelOfCourt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return levelOfCourt;
	}

	public LevelOfCourt loadOneRecord() {
		List<LevelOfCourt> levelOfCourts = levelOfCourtService.loadAll();

		if (levelOfCourts.isEmpty()) {
			persistAll();
			levelOfCourts = levelOfCourtService.loadAll();
		}

		return levelOfCourts.get(new Random().nextInt(levelOfCourts.size()));
	}

	public List<LevelOfCourt> getAllAsList() {

		if (levelOfCourts.isEmpty()) {

			createLevelOfCourtOne();
			createLevelOfCourtTwo();
			createLevelOfCourtThree();
			createLevelOfCourtFour();
			createLevelOfCourtFive();

		}

		return levelOfCourts;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (LevelOfCourt levelOfCourt : levelOfCourts) {
			try {
				levelOfCourtService.save(levelOfCourt);
			} catch (BusinessException be) {
				logger.warn(" LevelOfCourt " + levelOfCourt.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			LevelOfCourt levelOfCourt = createRandomLevelOfCourt();
			levelOfCourtService.save(levelOfCourt);
		}
	}

	public LevelOfCourt createRandomLevelOfCourt() {
		LevelOfCourt levelOfCourt = new LevelOfCourt();

		levelOfCourt.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("jurisdictionTestDataFactory");

		levelOfCourt
				.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
						.loadOneRecord());

		return levelOfCourt;
	}

}
