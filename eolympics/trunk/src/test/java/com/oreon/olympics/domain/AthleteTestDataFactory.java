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

import com.oreon.olympics.domain.service.AthleteService;

@Transactional
public class AthleteTestDataFactory extends AbstractTestDataFactory<Athlete> {

	List<Athlete> athletes = new ArrayList<Athlete>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	AthleteService athleteService;

	public AthleteService getAthleteService() {
		return athleteService;
	}

	public void setAthleteService(AthleteService athleteService) {
		this.athleteService = athleteService;
	}

	public void register(Athlete athlete) {
		athletes.add(athlete);
	}

	public Athlete createAthleteOne() {
		Athlete athlete = new Athlete();

		try {

			athlete.setFirstName("theta");
			athlete.setLastName("Eric");
			athlete.setDob(dateFormat.parse("2007.10.30 11:34:41 EDT"));
			athlete.setGender(domain.Gender.MALE);

			register(athlete);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return athlete;
	}

	public Athlete createAthleteTwo() {
		Athlete athlete = new Athlete();

		try {

			athlete.setFirstName("Mark");
			athlete.setLastName("Malissa");
			athlete.setDob(dateFormat.parse("2007.10.28 16:18:34 EDT"));
			athlete.setGender(domain.Gender.FEMALE);

			register(athlete);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return athlete;
	}

	public Athlete createAthleteThree() {
		Athlete athlete = new Athlete();

		try {

			athlete.setFirstName("gamma");
			athlete.setLastName("pi");
			athlete.setDob(dateFormat.parse("2007.11.19 09:21:21 EST"));
			athlete.setGender(domain.Gender.MALE);

			register(athlete);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return athlete;
	}

	public Athlete createAthleteFour() {
		Athlete athlete = new Athlete();

		try {

			athlete.setFirstName("epsilon");
			athlete.setLastName("gamma");
			athlete.setDob(dateFormat.parse("2007.10.25 03:57:29 EDT"));
			athlete.setGender(domain.Gender.MALE);

			register(athlete);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return athlete;
	}

	public Athlete createAthleteFive() {
		Athlete athlete = new Athlete();

		try {

			athlete.setFirstName("epsilon");
			athlete.setLastName("beta");
			athlete.setDob(dateFormat.parse("2007.10.19 12:58:01 EDT"));
			athlete.setGender(domain.Gender.FEMALE);

			register(athlete);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return athlete;
	}

	public Athlete loadOneRecord() {
		List<Athlete> athletes = athleteService.loadAll();

		if (athletes.isEmpty()) {
			persistAll();
			athletes = athleteService.loadAll();
		}

		return athletes.get(new Random().nextInt(athletes.size()));
	}

	public List<Athlete> getAllAsList() {

		if (athletes.isEmpty()) {
			createAthleteOne();
			createAthleteTwo();
			createAthleteThree();
			createAthleteFour();
			createAthleteFive();

		}

		return athletes;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Athlete athlete : athletes) {
			athleteService.save(athlete);
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
			Athlete athlete = createRandomAthlete();
			athleteService.save(athlete);
		}
	}

	public Athlete createRandomAthlete() {
		Athlete athlete = new Athlete();

		athlete.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		athlete.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		athlete.setDob((java.util.Date) RandomValueGeneratorFactory
				.createInstance("Date"));
		athlete.setGender((Gender) RandomValueGeneratorFactory
				.createInstance("Gender"));

		return athlete;
	}

}
