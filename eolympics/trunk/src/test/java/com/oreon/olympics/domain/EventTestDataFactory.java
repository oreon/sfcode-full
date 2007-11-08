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

import com.oreon.olympics.domain.service.EventService;

@Transactional
public class EventTestDataFactory extends AbstractTestDataFactory<Event> {

	List<Event> events = new ArrayList<Event>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	EventService eventService;

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void register(Event event) {
		events.add(event);
	}

	public Event createEventOne() {
		Event event = new Event();

		try {

			event.setGender(domain.Gender.FEMALE);
			event.setName("Eric");

			register(event);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return event;
	}

	public Event createEventTwo() {
		Event event = new Event();

		try {

			event.setGender(domain.Gender.FEMALE);
			event.setName("Wilson");

			register(event);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return event;
	}

	public Event createEventThree() {
		Event event = new Event();

		try {

			event.setGender(domain.Gender.MALE);
			event.setName("pi");

			register(event);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return event;
	}

	public Event createEventFour() {
		Event event = new Event();

		try {

			event.setGender(domain.Gender.FEMALE);
			event.setName("Malissa");

			register(event);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return event;
	}

	public Event createEventFive() {
		Event event = new Event();

		try {

			event.setGender(domain.Gender.FEMALE);
			event.setName("delta");

			register(event);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return event;
	}

	public Event loadOneRecord() {
		List<Event> events = eventService.loadAll();

		if (events.isEmpty()) {
			persistAll();
			events = eventService.loadAll();
		}

		return events.get(new Random().nextInt(events.size()));
	}

	public List<Event> getAllAsList() {

		if (events.isEmpty()) {
			createEventOne();
			createEventTwo();
			createEventThree();
			createEventFour();
			createEventFive();

		}

		return events;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Event event : events) {
			eventService.save(event);
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
			Event event = createRandomEvent();
			eventService.save(event);
		}
	}

	public Event createRandomEvent() {
		Event event = new Event();

		event.setGender((Gender) RandomValueGeneratorFactory
				.createInstance("Gender"));
		event.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return event;
	}

}
