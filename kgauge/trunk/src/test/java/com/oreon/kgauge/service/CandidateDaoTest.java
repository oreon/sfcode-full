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

			candidateInstance.setFirstName("Lavendar");
			candidateInstance.setLastName("beta");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.11.29 18:31:41 EST"));
			candidateInstance.getUser().setUsername("delta49668");
			candidateInstance.getUser().setPassword("Lavendar");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("John");
			candidateInstance.getContactDetails().setCity("pi");
			candidateInstance.getContactDetails().setState("Wilson");
			candidateInstance.getContactDetails().setCountry("delta");
			candidateInstance.getContactDetails().setZip("Eric");
			candidateInstance.getContactDetails().setPhone("Mark");
			candidateInstance.getContactDetails().setEmail("Lavendar85373");

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

				candidate.setFirstName("alpha");
				candidate.setLastName("gamma");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.12.22 13:02:50 EST"));
				candidate.getUser().setUsername("pi68216");
				candidate.getUser().setPassword("Mark");
				candidate.getUser().setEnabled(false);
				candidate.getContactDetails().setStreetAddress("alpha");
				candidate.getContactDetails().setCity("Lavendar");
				candidate.getContactDetails().setState("Eric");
				candidate.getContactDetails().setCountry("pi");
				candidate.getContactDetails().setZip("beta");
				candidate.getContactDetails().setPhone("pi");
				candidate.getContactDetails().setEmail("gamma43353");

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
			candidate.setLastName("delta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.22 11:24:28 EST"));
			candidate.getUser().setUsername("alpha42121");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("beta");
			candidate.getContactDetails().setCity("John");
			candidate.getContactDetails().setState("alpha");
			candidate.getContactDetails().setCountry("gamma");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Mark74528");

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
