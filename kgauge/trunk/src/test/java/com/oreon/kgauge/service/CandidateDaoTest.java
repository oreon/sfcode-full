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
			candidateInstance.setLastName("beta");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.02.19 23:14:26 EST"));
			candidateInstance.getUser().setUserName("Lavendar15647");
			candidateInstance.getUser().setPassword("delta");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("gamma");
			candidateInstance.getContactDetails().setCity("delta");
			candidateInstance.getContactDetails().setState("epsilon");
			candidateInstance.getContactDetails().setCountry("Wilson");
			candidateInstance.getContactDetails().setZip("alpha");
			candidateInstance.getContactDetails().setPhone("Malissa");
			candidateInstance.getContactDetails().setEmail("John41426");

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

				candidate.setFirstName("John");
				candidate.setLastName("Mark");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.02.14 08:56:04 EST"));
				candidate.getUser().setUserName("Lavendar5359");
				candidate.getUser().setPassword("Eric");
				candidate.getUser().setEnabled(true);
				candidate.getContactDetails().setStreetAddress("Malissa");
				candidate.getContactDetails().setCity("Mark");
				candidate.getContactDetails().setState("zeta");
				candidate.getContactDetails().setCountry("alpha");
				candidate.getContactDetails().setZip("delta");
				candidate.getContactDetails().setPhone("Malissa");
				candidate.getContactDetails().setEmail("Malissa50475");

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
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.29 16:48:51 EDT"));
			candidate.getUser().setUserName("delta60443");
			candidate.getUser().setPassword("epsilon");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("alpha");
			candidate.getContactDetails().setCity("alpha");
			candidate.getContactDetails().setState("epsilon");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("Lavendar2718");

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
