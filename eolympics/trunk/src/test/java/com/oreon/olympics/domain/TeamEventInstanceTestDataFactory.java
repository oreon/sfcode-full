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

import com.oreon.olympics.domain.service.TeamEventInstanceService;

@Transactional
public class TeamEventInstanceTestDataFactory
		extends
			AbstractTestDataFactory<TeamEventInstance> {

	List<TeamEventInstance> teamEventInstances = new ArrayList<TeamEventInstance>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	TeamEventInstanceService teamEventInstanceService;

	public TeamEventInstanceService getTeamEventInstanceService() {
		return teamEventInstanceService;
	}

	public void setTeamEventInstanceService(
			TeamEventInstanceService teamEventInstanceService) {
		this.teamEventInstanceService = teamEventInstanceService;
	}

	public void register(TeamEventInstance teamEventInstance) {
		teamEventInstances.add(teamEventInstance);
	}

	public TeamEventInstance createTeamEventInstanceOne() {
		TeamEventInstance teamEventInstance = new TeamEventInstance();

		try {

			register(teamEventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return teamEventInstance;
	}

	public TeamEventInstance createTeamEventInstanceTwo() {
		TeamEventInstance teamEventInstance = new TeamEventInstance();

		try {

			register(teamEventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return teamEventInstance;
	}

	public TeamEventInstance createTeamEventInstanceThree() {
		TeamEventInstance teamEventInstance = new TeamEventInstance();

		try {

			register(teamEventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return teamEventInstance;
	}

	public TeamEventInstance createTeamEventInstanceFour() {
		TeamEventInstance teamEventInstance = new TeamEventInstance();

		try {

			register(teamEventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return teamEventInstance;
	}

	public TeamEventInstance createTeamEventInstanceFive() {
		TeamEventInstance teamEventInstance = new TeamEventInstance();

		try {

			register(teamEventInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return teamEventInstance;
	}

	public TeamEventInstance loadOneRecord() {
		List<TeamEventInstance> teamEventInstances = teamEventInstanceService
				.loadAll();

		if (teamEventInstances.isEmpty()) {
			persistAll();
			teamEventInstances = teamEventInstanceService.loadAll();
		}

		return teamEventInstances.get(new Random().nextInt(teamEventInstances
				.size()));
	}

	public List<TeamEventInstance> getAllAsList() {

		if (teamEventInstances.isEmpty()) {
			createTeamEventInstanceOne();
			createTeamEventInstanceTwo();
			createTeamEventInstanceThree();
			createTeamEventInstanceFour();
			createTeamEventInstanceFive();

		}

		return teamEventInstances;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (TeamEventInstance teamEventInstance : teamEventInstances) {
			teamEventInstanceService.save(teamEventInstance);
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
			TeamEventInstance teamEventInstance = createRandomTeamEventInstance();
			teamEventInstanceService.save(teamEventInstance);
		}
	}

	public TeamEventInstance createRandomTeamEventInstance() {
		TeamEventInstance teamEventInstance = new TeamEventInstance();

		return teamEventInstance;
	}

}
