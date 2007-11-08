package com.oreon.olympics.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.olympics.domain.service.EventInstanceService;

@Transactional
public class EventInstanceTestDataFactory
		extends
			AbstractTestDataFactory<EventInstance> {

	List<EventInstance> eventInstances = new ArrayList<EventInstance>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	EventInstanceService eventInstanceService;

	public EventInstanceService getEventInstanceService() {
		return eventInstanceService;
	}

	public void setEventInstanceService(
			EventInstanceService eventInstanceService) {
		this.eventInstanceService = eventInstanceService;
	}

	public void register(EventInstance eventInstance) {
		eventInstances.add(eventInstance);
	}

	public EventInstance createEventInstanceOne() {
		EventInstance eventInstance = new EventInstance();

		try {

			register(eventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return eventInstance;
	}

	public EventInstance createEventInstanceTwo() {
		EventInstance eventInstance = new EventInstance();

		try {

			register(eventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return eventInstance;
	}

	public EventInstance createEventInstanceThree() {
		EventInstance eventInstance = new EventInstance();

		try {

			register(eventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return eventInstance;
	}

	public EventInstance createEventInstanceFour() {
		EventInstance eventInstance = new EventInstance();

		try {

			register(eventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return eventInstance;
	}

	public EventInstance createEventInstanceFive() {
		EventInstance eventInstance = new EventInstance();

		try {

			register(eventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return eventInstance;
	}

	public EventInstance loadOneRecord() {
		List<EventInstance> eventInstances = eventInstanceService.loadAll();

		if (eventInstances.isEmpty()) {
			persistAll();
			eventInstances = eventInstanceService.loadAll();
		}

		return eventInstances.get(new Random().nextInt(eventInstances.size()));
	}

	public List<EventInstance> getAllAsList() {

		if (eventInstances.isEmpty()) {
			createEventInstanceOne();
			createEventInstanceTwo();
			createEventInstanceThree();
			createEventInstanceFour();
			createEventInstanceFive();

		}

		return eventInstances;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (EventInstance eventInstance : eventInstances) {
			eventInstanceService.save(eventInstance);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			EventInstance eventInstance = createRandomEventInstance();
			eventInstanceService.save(eventInstance);
		}
	}

	public EventInstance createRandomEventInstance() {
		EventInstance eventInstance = new EventInstance();

		return eventInstance;
	}

}
