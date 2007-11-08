package com.oreon.olympics.domain.service;

import com.oreon.olympics.domain.Athlete;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class AthleteDaoTest extends AbstractJpaTests {

	protected Athlete athleteInstance = new Athlete();

	protected AthleteService athleteService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setAthleteService(AthleteService athleteService) {
		this.athleteService = athleteService;
	}

	protected TestDataFactory athleteTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("athleteTestDataFactory");

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

			athleteInstance.setFirstName("John");
			athleteInstance.setLastName("Malissa");
			athleteInstance.setDob(dateFormat.parse("2007.10.15 19:10:48 EDT"));
			athleteInstance.setGender(domain.Gender.MALE);

			athleteService.save(athleteInstance);
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
			Athlete athlete = new Athlete();

			try {

				athlete.setFirstName("alpha");
				athlete.setLastName("gamma");
				athlete.setDob(dateFormat.parse("2007.11.12 04:25:14 EST"));
				athlete.setGender(domain.Gender.MALE);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			athleteService.save(athlete);
			assertNotNull(athlete.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Athlete athlete = (Athlete) athleteTestDataFactory.loadOneRecord();

			athlete.setFirstName("theta");
			athlete.setLastName("pi");
			athlete.setDob(dateFormat.parse("2007.11.29 09:03:36 EST"));
			athlete.setGender(domain.Gender.FEMALE);

			athleteService.save(athlete);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(athleteService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Athlete athlete = athleteService.load(athleteInstance.getId());
			assertNotNull(athlete.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Athlete> athletes = athleteService
					.searchByExample(athleteInstance);
			assertTrue(!athletes.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
