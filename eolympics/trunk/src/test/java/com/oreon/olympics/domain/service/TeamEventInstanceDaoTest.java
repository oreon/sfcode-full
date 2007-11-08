package com.oreon.olympics.domain.service;

import com.oreon.olympics.domain.TeamEventInstance;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class TeamEventInstanceDaoTest extends AbstractJpaTests {

	protected TeamEventInstance teamEventInstanceInstance = new TeamEventInstance();

	protected TeamEventInstanceService teamEventInstanceService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setTeamEventInstanceService(
			TeamEventInstanceService teamEventInstanceService) {
		this.teamEventInstanceService = teamEventInstanceService;
	}

	protected TestDataFactory teamEventInstanceTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("teamEventInstanceTestDataFactory");

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

			teamEventInstanceService.save(teamEventInstanceInstance);
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
			TeamEventInstance teamEventInstance = new TeamEventInstance();

			try {

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			teamEventInstanceService.save(teamEventInstance);
			assertNotNull(teamEventInstance.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			TeamEventInstance teamEventInstance = (TeamEventInstance) teamEventInstanceTestDataFactory
					.loadOneRecord();

			teamEventInstanceService.save(teamEventInstance);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(teamEventInstanceService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			TeamEventInstance teamEventInstance = teamEventInstanceService
					.load(teamEventInstanceInstance.getId());
			assertNotNull(teamEventInstance.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<TeamEventInstance> teamEventInstances = teamEventInstanceService
					.searchByExample(teamEventInstanceInstance);
			assertTrue(!teamEventInstances.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
