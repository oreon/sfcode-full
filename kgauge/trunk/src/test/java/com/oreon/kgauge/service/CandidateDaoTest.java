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

			candidateInstance.setFirstName("John");
			candidateInstance.setLastName("pi");
			candidateInstance.setDateOfBirth(dateFormat
					.parse("2009.01.10 20:54:00 EST"));
			candidateInstance.getUser().setUsername("gamma53944");
			candidateInstance.getUser().setPassword("gamma");
			candidateInstance.getUser().setEnabled(true);
			candidateInstance.getContactDetails().setStreetAddress("John");
			candidateInstance.getContactDetails().setCity("Mark");
			candidateInstance.getContactDetails().setState("zeta");
			candidateInstance.getContactDetails().setCountry("gamma");
			candidateInstance.getContactDetails().setZip("epsilon");
			candidateInstance.getContactDetails().setPhone("Wilson");
			candidateInstance.getContactDetails().setEmail("gamma89121");

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
				candidate.setLastName("delta");
				candidate.setDateOfBirth(dateFormat
						.parse("2009.01.05 04:10:40 EST"));
				candidate.getUser().setUsername("Mark89431");
				candidate.getUser().setPassword("epsilon");
				candidate.getUser().setEnabled(false);
				candidate.getContactDetails().setStreetAddress("Wilson");
				candidate.getContactDetails().setCity("Eric");
				candidate.getContactDetails().setState("beta");
				candidate.getContactDetails().setCountry("Lavendar");
				candidate.getContactDetails().setZip("alpha");
				candidate.getContactDetails().setPhone("delta");
				candidate.getContactDetails().setEmail("pi50182");

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
			candidate.setLastName("epsilon");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.17 21:01:13 EST"));
			candidate.getUser().setUsername("Mark13362");
			candidate.getUser().setPassword("zeta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Lavendar");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("Eric");
			candidate.getContactDetails().setCountry("Malissa");
			candidate.getContactDetails().setZip("Wilson");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Malissa59711");

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
