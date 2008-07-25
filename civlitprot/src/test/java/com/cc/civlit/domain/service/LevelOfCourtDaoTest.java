package com.cc.civlit.domain.service;

import com.cc.civlit.domain.courtdivisions.LevelOfCourt;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class LevelOfCourtDaoTest extends AbstractJpaTests {

	protected LevelOfCourt levelOfCourtInstance = new LevelOfCourt();

	protected LevelOfCourtService levelOfCourtService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setLevelOfCourtService(LevelOfCourtService levelOfCourtService) {
		this.levelOfCourtService = levelOfCourtService;
	}

	protected TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("levelOfCourtTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			levelOfCourtInstance.setName("delta");

			TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("jurisdictionTestDataFactory");

			levelOfCourtInstance
					.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
							.loadOneRecord());

			levelOfCourtService.save(levelOfCourtInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			LevelOfCourt levelOfCourt = new LevelOfCourt();

			try {

				levelOfCourt.setName("alpha");

				TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("jurisdictionTestDataFactory");

				levelOfCourt
						.setJurisdiction((com.cc.civlit.domain.courtdivisions.Jurisdiction) jurisdictionTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			levelOfCourtService.save(levelOfCourt);
			assertNotNull(levelOfCourt.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			LevelOfCourt levelOfCourt = (LevelOfCourt) levelOfCourtTestDataFactory
					.loadOneRecord();

			levelOfCourt.setName("delta");

			levelOfCourtService.save(levelOfCourt);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(levelOfCourtService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = levelOfCourtService.getCount();
			LevelOfCourt levelOfCourt = (LevelOfCourt) levelOfCourtTestDataFactory
					.loadOneRecord();
			levelOfCourtService.delete(levelOfCourt);
			newCount = levelOfCourtService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			LevelOfCourt levelOfCourt = levelOfCourtService
					.load(levelOfCourtInstance.getId());
			assertNotNull(levelOfCourt.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<LevelOfCourt> levelOfCourts = levelOfCourtService
					.searchByExample(levelOfCourtInstance);
			assertTrue(!levelOfCourts.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
