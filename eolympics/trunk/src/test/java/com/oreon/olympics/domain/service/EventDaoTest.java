package com.oreon.olympics.domain.service;

import com.oreon.olympics.domain.Event;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class EventDaoTest extends AbstractJpaTests {

	protected Event eventInstance = new Event();

	protected EventService eventService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	protected TestDataFactory eventTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("eventTestDataFactory");

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

			eventInstance.setGender(domain.Gender.FEMALE);
			eventInstance.setName("theta");

			eventService.save(eventInstance);
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
			Event event = new Event();

			try {

				event.setGender(domain.Gender.FEMALE);
				event.setName("epsilon");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			eventService.save(event);
			assertNotNull(event.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Event event = (Event) eventTestDataFactory.loadOneRecord();

			event.setGender(domain.Gender.MALE);
			event.setName("Wilson");

			eventService.save(event);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(eventService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Event event = eventService.load(eventInstance.getId());
			assertNotNull(event.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Event> events = eventService.searchByExample(eventInstance);
			assertTrue(!events.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
