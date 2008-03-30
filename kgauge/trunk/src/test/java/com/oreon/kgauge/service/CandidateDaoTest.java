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

			candidateInstance.setFirstName("Eric");
			candidateInstance.setLastName("John");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.04.22 12:30:46 EDT"));
			candidateInstance.getUser().setUsername("pi15617");
			candidateInstance.getUser().setPassword("zeta");
			candidateInstance.getUser().setEnabled(true);
			candidateInstance.getContactDetails().setStreetAddress("delta");
			candidateInstance.getContactDetails().setCity("alpha");
			candidateInstance.getContactDetails().setState("gamma");
			candidateInstance.getContactDetails().setCountry("Lavendar");
			candidateInstance.getContactDetails().setZip("beta");
			candidateInstance.getContactDetails().setPhone("Wilson");
			candidateInstance.getContactDetails().setEmail("zeta76897");

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

				candidate.setFirstName("pi");
				candidate.setLastName("theta");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.03.24 08:17:26 EDT"));
				candidate.getUser().setUsername("alpha61587");
				candidate.getUser().setPassword("Lavendar");
				candidate.getUser().setEnabled(false);
				candidate.getContactDetails().setStreetAddress("Lavendar");
				candidate.getContactDetails().setCity("pi");
				candidate.getContactDetails().setState("Eric");
				candidate.getContactDetails().setCountry("epsilon");
				candidate.getContactDetails().setZip("Mark");
				candidate.getContactDetails().setPhone("Lavendar");
				candidate.getContactDetails().setEmail("beta63477");

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

			candidate.setFirstName("Lavendar");
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.21 19:34:38 EDT"));
			candidate.getUser().setUsername("Wilson9592");
			candidate.getUser().setPassword("epsilon");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("epsilon");
			candidate.getContactDetails().setCity("delta");
			candidate.getContactDetails().setState("zeta");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("beta");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("epsilon81761");

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
