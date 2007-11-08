package com.oreon.olympics.domain.service;

import com.oreon.olympics.domain.Tournament;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class TournamentDaoTest extends AbstractJpaTests {

	protected Tournament tournamentInstance = new Tournament();

	protected TournamentService tournamentService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	protected TestDataFactory tournamentTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("tournamentTestDataFactory");

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

			tournamentInstance.setName("delta");

			tournamentService.save(tournamentInstance);
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
			Tournament tournament = new Tournament();

			try {

				tournament.setName("Eric");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			tournamentService.save(tournament);
			assertNotNull(tournament.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Tournament tournament = (Tournament) tournamentTestDataFactory
					.loadOneRecord();

			tournament.setName("epsilon");

			tournamentService.save(tournament);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(tournamentService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Tournament tournament = tournamentService.load(tournamentInstance
					.getId());
			assertNotNull(tournament.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Tournament> tournaments = tournamentService
					.searchByExample(tournamentInstance);
			assertTrue(!tournaments.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
