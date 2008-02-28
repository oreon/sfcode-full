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

			candidateInstance.setFirstName("theta");
			candidateInstance.setLastName("Malissa");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.03.02 03:46:46 GMT"));
			candidateInstance.getUser().setUserName("theta44881");
			candidateInstance.getUser().setPassword("John");
			candidateInstance.getUser().setEnabled(true);
			candidateInstance.getContactDetails().setStreetAddress("Eric");
			candidateInstance.getContactDetails().setCity("Malissa");
			candidateInstance.getContactDetails().setState("Eric");
			candidateInstance.getContactDetails().setCountry("pi");
			candidateInstance.getContactDetails().setZip("alpha");
			candidateInstance.getContactDetails().setPhone("Lavendar");
			candidateInstance.getContactDetails().setEmail("beta67351");

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

				candidate.setFirstName("theta");
				candidate.setLastName("gamma");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.02.19 20:03:26 GMT"));
				candidate.getUser().setUserName("pi44679");
				candidate.getUser().setPassword("alpha");
				candidate.getUser().setEnabled(false);
				candidate.getContactDetails().setStreetAddress("Mark");
				candidate.getContactDetails().setCity("alpha");
				candidate.getContactDetails().setState("Eric");
				candidate.getContactDetails().setCountry("Eric");
				candidate.getContactDetails().setZip("Mark");
				candidate.getContactDetails().setPhone("John");
				candidate.getContactDetails().setEmail("Wilson90519");

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

			candidate.setFirstName("Eric");
			candidate.setLastName("zeta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.12 01:52:53 GMT"));
			candidate.getUser().setUserName("theta47573");
			candidate.getUser().setPassword("Mark");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("delta");
			candidate.getContactDetails().setState("epsilon");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("Mark");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("Wilson72545");

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
