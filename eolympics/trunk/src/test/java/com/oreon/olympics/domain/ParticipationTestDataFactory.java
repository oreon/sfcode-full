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

import com.oreon.olympics.domain.service.ParticipationService;

@Transactional
public class ParticipationTestDataFactory
		extends
			AbstractTestDataFactory<Participation> {

	List<Participation> participations = new ArrayList<Participation>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	ParticipationService participationService;

	public ParticipationService getParticipationService() {
		return participationService;
	}

	public void setParticipationService(
			ParticipationService participationService) {
		this.participationService = participationService;
	}

	public void register(Participation participation) {
		participations.add(participation);
	}

	public Participation createParticipationOne() {
		Participation participation = new Participation();

		try {

			participation.setRank("unknown attrib type");

			register(participation);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return participation;
	}

	public Participation createParticipationTwo() {
		Participation participation = new Participation();

		try {

			participation.setRank("unknown attrib type");

			register(participation);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return participation;
	}

	public Participation createParticipationThree() {
		Participation participation = new Participation();

		try {

			participation.setRank("unknown attrib type");

			register(participation);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return participation;
	}

	public Participation createParticipationFour() {
		Participation participation = new Participation();

		try {

			participation.setRank("unknown attrib type");

			register(participation);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return participation;
	}

	public Participation createParticipationFive() {
		Participation participation = new Participation();

		try {

			participation.setRank("unknown attrib type");

			register(participation);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return participation;
	}

	public Participation loadOneRecord() {
		List<Participation> participations = participationService.loadAll();

		if (participations.isEmpty()) {
			persistAll();
			participations = participationService.loadAll();
		}

		return participations.get(new Random().nextInt(participations.size()));
	}

	public List<Participation> getAllAsList() {

		if (participations.isEmpty()) {
			createParticipationOne();
			createParticipationTwo();
			createParticipationThree();
			createParticipationFour();
			createParticipationFive();

		}

		return participations;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Participation participation : participations) {
			participationService.save(participation);
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
			Participation participation = createRandomParticipation();
			participationService.save(participation);
		}
	}

	public Participation createRandomParticipation() {
		Participation participation = new Participation();

		participation.setRank((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));

		return participation;
	}

}
