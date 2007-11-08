package com.oreon.olympics.domain.service;

import com.oreon.olympics.domain.EventInstance;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class EventInstanceDaoTest extends AbstractJpaTests {

	protected EventInstance eventInstanceInstance = new EventInstance();

	protected EventInstanceService eventInstanceService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setEventInstanceService(
			EventInstanceService eventInstanceService) {
		this.eventInstanceService = eventInstanceService;
	}

	protected TestDataFactory eventInstanceTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("eventInstanceTestDataFactory");

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

			eventInstanceService.save(eventInstanceInstance);
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
			EventInstance eventInstance = new EventInstance();

			try {

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			eventInstanceService.save(eventInstance);
			assertNotNull(eventInstance.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			EventInstance eventInstance = (EventInstance) eventInstanceTestDataFactory
					.loadOneRecord();

			eventInstanceService.save(eventInstance);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(eventInstanceService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			EventInstance eventInstance = eventInstanceService
					.load(eventInstanceInstance.getId());
			assertNotNull(eventInstance.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<EventInstance> eventInstances = eventInstanceService
					.searchByExample(eventInstanceInstance);
			assertTrue(!eventInstances.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
