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
			candidateInstance.setLastName("theta");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2008.12.02 15:24:53 EST"));
			candidateInstance.getUser().setUsername("Mark87983");
			candidateInstance.getUser().setPassword("Lavendar");
			candidateInstance.getUser().setEnabled(false);
			candidateInstance.getContactDetails().setStreetAddress("Lavendar");
			candidateInstance.getContactDetails().setCity("epsilon");
			candidateInstance.getContactDetails().setState("Wilson");
			candidateInstance.getContactDetails().setCountry("John");
			candidateInstance.getContactDetails().setZip("zeta");
			candidateInstance.getContactDetails().setPhone("Wilson");
			candidateInstance.getContactDetails().setEmail("epsilon35394");

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
				candidate.setLastName("delta");
				candidate.setDateOfBirth(dateFormat
						.parse("2008.11.29 03:32:06 EST"));
				candidate.getUser().setUsername("Eric34848");
				candidate.getUser().setPassword("Malissa");
				candidate.getUser().setEnabled(false);
				candidate.getContactDetails().setStreetAddress("epsilon");
				candidate.getContactDetails().setCity("alpha");
				candidate.getContactDetails().setState("gamma");
				candidate.getContactDetails().setCountry("zeta");
				candidate.getContactDetails().setZip("zeta");
				candidate.getContactDetails().setPhone("alpha");
				candidate.getContactDetails().setEmail("Lavendar34556");

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

			candidate.setFirstName("alpha");
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.29 18:23:48 EST"));
			candidate.getUser().setUsername("gamma93902");
			candidate.getUser().setPassword("epsilon");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("Mark");
			candidate.getContactDetails().setZip("pi");
			candidate.getContactDetails().setPhone("John");
			candidate.getContactDetails().setEmail("pi68747");

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
