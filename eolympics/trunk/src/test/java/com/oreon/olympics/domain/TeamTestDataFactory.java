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

import com.oreon.olympics.domain.service.TeamService;

@Transactional
public class TeamTestDataFactory extends AbstractTestDataFactory<Team> {

	List<Team> teams = new ArrayList<Team>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	TeamService teamService;

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void register(Team team) {
		teams.add(team);
	}

	public Team createTeamOne() {
		Team team = new Team();

		try {

			register(team);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return team;
	}

	public Team createTeamTwo() {
		Team team = new Team();

		try {

			register(team);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return team;
	}

	public Team createTeamThree() {
		Team team = new Team();

		try {

			register(team);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return team;
	}

	public Team createTeamFour() {
		Team team = new Team();

		try {

			register(team);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return team;
	}

	public Team createTeamFive() {
		Team team = new Team();

		try {

			register(team);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return team;
	}

	public Team loadOneRecord() {
		List<Team> teams = teamService.loadAll();

		if (teams.isEmpty()) {
			persistAll();
			teams = teamService.loadAll();
		}

		return teams.get(new Random().nextInt(teams.size()));
	}

	public List<Team> getAllAsList() {

		if (teams.isEmpty()) {
			createTeamOne();
			createTeamTwo();
			createTeamThree();
			createTeamFour();
			createTeamFive();

		}

		return teams;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Team team : teams) {
			teamService.save(team);
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
			Team team = createRandomTeam();
			teamService.save(team);
		}
	}

	public Team createRandomTeam() {
		Team team = new Team();

		return team;
	}

}
