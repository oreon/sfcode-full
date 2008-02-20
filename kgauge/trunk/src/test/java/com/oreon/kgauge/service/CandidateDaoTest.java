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

			candidateInstance.setFirstName("pi");
			candidateInstance.setLastName("Wilson");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.02.03 06:16:50 EST"));
			candidateInstance.getUser().setUserName("Lavendar70229");
			candidateInstance.getUser().setPassword("pi");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("Eric");
			candidateInstance.getContactDetails().setCity("epsilon");
			candidateInstance.getContactDetails().setState("alpha");
			candidateInstance.getContactDetails().setCountry("gamma");
			candidateInstance.getContactDetails().setZip("Lavendar");
			candidateInstance.getContactDetails().setPhone("alpha");
			candidateInstance.getContactDetails().setEmail("Lavendar43140");

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

				candidate.setFirstName("delta");
				candidate.setLastName("pi");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.03.10 08:03:30 EDT"));
				candidate.getUser().setUserName("alpha17556");
				candidate.getUser().setPassword("zeta");
				candidate.getUser().setEnabled(true);
				candidate.getContactDetails().setStreetAddress("alpha");
				candidate.getContactDetails().setCity("Malissa");
				candidate.getContactDetails().setState("Eric");
				candidate.getContactDetails().setCountry("zeta");
				candidate.getContactDetails().setZip("delta");
				candidate.getContactDetails().setPhone("Eric");
				candidate.getContactDetails().setEmail("theta49429");

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
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.01.28 16:45:09 EST"));
			candidate.getUser().setUserName("alpha72970");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("gamma");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("alpha");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("Mark14822");

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
