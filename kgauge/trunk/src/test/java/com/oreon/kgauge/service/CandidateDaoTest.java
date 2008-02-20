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

			candidateInstance.setFirstName("Mark");
			candidateInstance.setLastName("theta");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.02.08 19:50:40 EST"));
			candidateInstance.getUser().setUserName("delta90087");
			candidateInstance.getUser().setPassword("pi");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("epsilon");
			candidateInstance.getContactDetails().setCity("Wilson");
			candidateInstance.getContactDetails().setState("zeta");
			candidateInstance.getContactDetails().setCountry("Malissa");
			candidateInstance.getContactDetails().setZip("John");
			candidateInstance.getContactDetails().setPhone("beta");
			candidateInstance.getContactDetails().setEmail("Mark45157");

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
				candidate.setLastName("beta");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.02.02 02:10:40 EST"));
				candidate.getUser().setUserName("Mark4928");
				candidate.getUser().setPassword("theta");
				candidate.getUser().setEnabled(true);
				candidate.getContactDetails().setStreetAddress("Malissa");
				candidate.getContactDetails().setCity("Malissa");
				candidate.getContactDetails().setState("Malissa");
				candidate.getContactDetails().setCountry("theta");
				candidate.getContactDetails().setZip("pi");
				candidate.getContactDetails().setPhone("delta");
				candidate.getContactDetails().setEmail("beta86339");

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

			candidate.setFirstName("Malissa");
			candidate.setLastName("alpha");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.02 02:04:00 EST"));
			candidate.getUser().setUserName("Eric6250");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("Malissa");
			candidate.getContactDetails().setCountry("gamma");
			candidate.getContactDetails().setZip("Lavendar");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("John56178");

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
		long count, newCount, diff = 0;
		count = candidateService.getCount();
		Candidate candidate = (Candidate) candidateTestDataFactory
				.loadOneRecord();
		candidateService.delete(candidate);
		newCount = candidateService.getCount();
		diff = newCount - count;
		try {
			assertEquals(diff, 0);
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
