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

			candidateInstance.setFirstName("gamma");
			candidateInstance.setLastName("Lavendar");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.11.15 05:33:47 EST"));
			candidateInstance.getUser().setUsername("delta98662");
			candidateInstance.getUser().setPassword("epsilon");
			candidateInstance.getUser().setEnabled(true);
			candidateInstance.getContactDetails().setStreetAddress("Wilson");
			candidateInstance.getContactDetails().setCity("Mark");
			candidateInstance.getContactDetails().setState("Eric");
			candidateInstance.getContactDetails().setCountry("epsilon");
			candidateInstance.getContactDetails().setZip("Lavendar");
			candidateInstance.getContactDetails().setPhone("pi");
			candidateInstance.getContactDetails().setEmail("zeta13267");

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
				candidate.setLastName("Wilson");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.11.09 02:54:56 EST"));
				candidate.getUser().setUsername("pi12152");
				candidate.getUser().setPassword("epsilon");
				candidate.getUser().setEnabled(true);
				candidate.getContactDetails().setStreetAddress("Lavendar");
				candidate.getContactDetails().setCity("Mark");
				candidate.getContactDetails().setState("gamma");
				candidate.getContactDetails().setCountry("pi");
				candidate.getContactDetails().setZip("epsilon");
				candidate.getContactDetails().setPhone("pi");
				candidate.getContactDetails().setEmail("Wilson80658");

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

			candidate.setFirstName("theta");
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.20 10:00:27 EST"));
			candidate.getUser().setUsername("Mark39522");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("zeta");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("Mark64600");

			candidateService.save(candidate);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(candidateService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=candidateService.getCount();
			Candidate candidate = (Candidate)candidateTestDataFactory.loadOneRecord();					
			candidateService.delete(candidate);
			newCount=candidateService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

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

	/////////////////// Queries //////////////////////////////////

	public void testFindExamInstances() {

		//Long candidateId  = 0;

		//List retList = candidateService.findExamInstances(candidateId);
	}

	public void testFindNumberOfCertifications() {

		//Long retLong = candidateService.findNumberOfCertifications();
	}

}
