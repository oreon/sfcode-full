package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.Candidate;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class CandidateDaoTest extends AbstractJpaTests {

	protected Candidate candidateInstance = new Candidate();

	protected CandidateService candidateService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	protected TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("candidateTestDataFactory");

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

			candidateInstance.setFirstName("alpha");
			candidateInstance.setLastName("pi");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.04.28 23:09:43 EDT"));
			candidateInstance.getUser().setUsername("beta16071");
			candidateInstance.getUser().setPassword("Mark");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("pi");
			candidateInstance.getContactDetails().setCity("Eric");
			candidateInstance.getContactDetails().setState("Malissa");
			candidateInstance.getContactDetails().setCountry("Mark");
			candidateInstance.getContactDetails().setZip("Lavendar");
			candidateInstance.getContactDetails().setPhone("alpha");
			candidateInstance.getContactDetails().setEmail("John13125");

			candidateService.save(candidateInstance);
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
			Candidate candidate = new Candidate();

			try {

				candidate.setFirstName("Wilson");
				candidate.setLastName("Malissa");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.05.02 01:00:16 EDT"));
				candidate.getUser().setUsername("Malissa10494");
				candidate.getUser().setPassword("Eric");
				candidate.getUser().setEnabled(true);
				candidate.getContactDetails().setStreetAddress("Wilson");
				candidate.getContactDetails().setCity("John");
				candidate.getContactDetails().setState("alpha");
				candidate.getContactDetails().setCountry("Mark");
				candidate.getContactDetails().setZip("Eric");
				candidate.getContactDetails().setPhone("Lavendar");
				candidate.getContactDetails().setEmail("Mark50214");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			candidateService.save(candidate);
			assertNotNull(candidate.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Candidate candidate = (Candidate) candidateTestDataFactory
					.loadOneRecord();

			candidate.setFirstName("Mark");
			candidate.setLastName("beta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.29 19:43:36 EDT"));
			candidate.getUser().setUsername("pi78355");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("Malissa");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("Mark32465");

			candidateService.save(candidate);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(candidateService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = candidateService.getCount();
			Candidate candidate = (Candidate) candidateTestDataFactory
					.loadOneRecord();
			candidateService.delete(candidate);
			newCount = candidateService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			Candidate candidate = candidateService.load(candidateInstance
					.getId());
			assertNotNull(candidate.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByUsername() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Candidate with username ",
				candidateService.findByUsername(candidateInstance.getUser()
						.getUsername()));
		//assertNull("Found a Candidate with username YYY", candidateService.findByUsername("YYY"));			

	}

	public void testFindByEmail() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Candidate with email ", candidateService
				.findByEmail(candidateInstance.getContactDetails().getEmail()));
		//assertNull("Found a Candidate with email YYY", candidateService.findByEmail("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<Candidate> candidates = candidateService
					.searchByExample(candidateInstance);
			assertTrue(!candidates.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
