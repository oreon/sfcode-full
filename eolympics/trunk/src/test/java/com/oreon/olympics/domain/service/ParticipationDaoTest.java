package com.oreon.olympics.domain.service;

import com.oreon.olympics.domain.Participation;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class ParticipationDaoTest extends AbstractJpaTests {

	protected Participation participationInstance = new Participation();

	protected ParticipationService participationService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setParticipationService(
			ParticipationService participationService) {
		this.participationService = participationService;
	}

	protected TestDataFactory participationTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("participationTestDataFactory");

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

			participationInstance.setRank("unknown attrib type");

			participationService.save(participationInstance);
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
			Participation participation = new Participation();

			try {

				participation.setRank("unknown attrib type");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			participationService.save(participation);
			assertNotNull(participation.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Participation participation = (Participation) participationTestDataFactory
					.loadOneRecord();

			participation.setRank("unknown attrib type");

			participationService.save(participation);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(participationService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Participation participation = participationService
					.load(participationInstance.getId());
			assertNotNull(participation.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Participation> participations = participationService
					.searchByExample(participationInstance);
			assertTrue(!participations.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
