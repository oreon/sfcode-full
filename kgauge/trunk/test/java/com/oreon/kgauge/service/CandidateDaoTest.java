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
			candidateInstance.setLastName("delta");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.06.08 22:54:40 EDT"));
			candidateInstance.getUser().setUsername("beta92394");
			candidateInstance.getUser().setPassword("gamma");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("Eric");
			candidateInstance.getContactDetails().setCity("Eric");
			candidateInstance.getContactDetails().setState("John");
			candidateInstance.getContactDetails().setCountry("pi");
			candidateInstance.getContactDetails().setZip("zeta");
			candidateInstance.getContactDetails().setPhone("Wilson");
			candidateInstance.getContactDetails().setEmail("Eric88722");

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

				candidate.setFirstName("Mark");
				candidate.setLastName("alpha");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.05.29 16:14:07 EDT"));
				candidate.getUser().setUsername("beta65545");
				candidate.getUser().setPassword("theta");
				candidate.getUser().setEnabled(false);
				candidate.getContactDetails().setStreetAddress("zeta");
				candidate.getContactDetails().setCity("zeta");
				candidate.getContactDetails().setState("beta");
				candidate.getContactDetails().setCountry("Wilson");
				candidate.getContactDetails().setZip("Lavendar");
				candidate.getContactDetails().setPhone("pi");
				candidate.getContactDetails().setEmail("Eric73080");

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
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.01 23:28:33 EDT"));
			candidate.getUser().setUsername("John65999");
			candidate.getUser().setPassword("John");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("alpha");
			candidate.getContactDetails().setCity("Wilson");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("Lavendar");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Malissa14657");

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
