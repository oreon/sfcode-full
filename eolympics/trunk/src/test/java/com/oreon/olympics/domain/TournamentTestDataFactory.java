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

import com.oreon.olympics.domain.service.TournamentService;

@Transactional
public class TournamentTestDataFactory
		extends
			AbstractTestDataFactory<Tournament> {

	List<Tournament> tournaments = new ArrayList<Tournament>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	TournamentService tournamentService;

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public void register(Tournament tournament) {
		tournaments.add(tournament);
	}

	public Tournament createTournamentOne() {
		Tournament tournament = new Tournament();

		try {

			tournament.setName("pi");

			register(tournament);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tournament;
	}

	public Tournament createTournamentTwo() {
		Tournament tournament = new Tournament();

		try {

			tournament.setName("alpha");

			register(tournament);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tournament;
	}

	public Tournament createTournamentThree() {
		Tournament tournament = new Tournament();

		try {

			tournament.setName("alpha");

			register(tournament);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tournament;
	}

	public Tournament createTournamentFour() {
		Tournament tournament = new Tournament();

		try {

			tournament.setName("gamma");

			register(tournament);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tournament;
	}

	public Tournament createTournamentFive() {
		Tournament tournament = new Tournament();

		try {

			tournament.setName("zeta");

			register(tournament);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tournament;
	}

	public Tournament loadOneRecord() {
		List<Tournament> tournaments = tournamentService.loadAll();

		if (tournaments.isEmpty()) {
			persistAll();
			tournaments = tournamentService.loadAll();
		}

		return tournaments.get(new Random().nextInt(tournaments.size()));
	}

	public List<Tournament> getAllAsList() {

		if (tournaments.isEmpty()) {
			createTournamentOne();
			createTournamentTwo();
			createTournamentThree();
			createTournamentFour();
			createTournamentFive();

		}

		return tournaments;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Tournament tournament : tournaments) {
			tournamentService.save(tournament);
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
			Tournament tournament = createRandomTournament();
			tournamentService.save(tournament);
		}
	}

	public Tournament createRandomTournament() {
		Tournament tournament = new Tournament();

		tournament.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return tournament;
	}

}
