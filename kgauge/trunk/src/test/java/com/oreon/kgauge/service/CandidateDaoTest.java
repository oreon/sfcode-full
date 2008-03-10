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

			candidateInstance.setFirstName("zeta");
			candidateInstance.setLastName("pi");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.02.16 08:15:22 EST"));
			candidateInstance.getUser().setUserName("beta21446");
			candidateInstance.getUser().setPassword("Lavendar");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("John");
			candidateInstance.getContactDetails().setCity("Mark");
			candidateInstance.getContactDetails().setState("epsilon");
			candidateInstance.getContactDetails().setCountry("epsilon");
			candidateInstance.getContactDetails().setZip("Wilson");
			candidateInstance.getContactDetails().setPhone("alpha");
			candidateInstance.getContactDetails().setEmail("Eric45354");

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

				candidate.setFirstName("zeta");
				candidate.setLastName("Mark");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.03.03 21:00:57 EST"));
				candidate.getUser().setUserName("epsilon62634");
				candidate.getUser().setPassword("theta");
				candidate.getUser().setEnabled(false);
				candidate.getContactDetails().setStreetAddress("Wilson");
				candidate.getContactDetails().setCity("Eric");
				candidate.getContactDetails().setState("Lavendar");
				candidate.getContactDetails().setCountry("Lavendar");
				candidate.getContactDetails().setZip("alpha");
				candidate.getContactDetails().setPhone("Lavendar");
				candidate.getContactDetails().setEmail("beta5972");

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

			candidate.setFirstName("zeta");
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.17 16:24:17 EDT"));
			candidate.getUser().setUserName("John7225");
			candidate.getUser().setPassword("zeta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("beta");
			candidate.getContactDetails().setCity("delta");
			candidate.getContactDetails().setState("Lavendar");
			candidate.getContactDetails().setCountry("gamma");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("alpha");
			candidate.getContactDetails().setEmail("gamma10259");

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

	public void testFindByUserName() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Candidate with userName ",
				candidateService.findByUserName(candidateInstance.getUser()
						.getUserName()));
		//assertNull("Found a Candidate with userName YYY", candidateService.findByUserName("YYY"));			

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
