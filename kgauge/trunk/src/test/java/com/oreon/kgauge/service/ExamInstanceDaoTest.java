package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.ExamInstance;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class ExamInstanceDaoTest extends AbstractJpaTests {

	protected ExamInstance examInstanceInstance = new ExamInstance();

	protected ExamInstanceService examInstanceService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setExamInstanceService(ExamInstanceService examInstanceService) {
		this.examInstanceService = examInstanceService;
	}

	protected TestDataFactory examInstanceTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("examInstanceTestDataFactory");

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

			examInstanceInstance.setDateOfExam(dateFormat
					.parse("2008.12.10 10:25:01 EST"));
			examInstanceInstance.setMaxScore(2483);
			examInstanceInstance.setCandidateScore(8304);
			examInstanceInstance.setCertified(false);
			examInstanceInstance.setPercentage(22.67);

			TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("candidateTestDataFactory");

			examInstanceInstance
					.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
							.loadOneRecord());

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			examInstanceInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			examInstanceService.save(examInstanceInstance);
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
			ExamInstance examInstance = new ExamInstance();

			try {

				examInstance.setDateOfExam(dateFormat
						.parse("2008.12.08 21:17:48 EST"));
				examInstance.setMaxScore(6093);
				examInstance.setCandidateScore(7958);
				examInstance.setCertified(false);
				examInstance.setPercentage(1.5);

				TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("candidateTestDataFactory");

				examInstance
						.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
								.loadOneRecord());

				TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("examTestDataFactory");

				examInstance
						.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			examInstanceService.save(examInstance);
			assertNotNull(examInstance.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			ExamInstance examInstance = (ExamInstance) examInstanceTestDataFactory
					.loadOneRecord();

			examInstance.setDateOfExam(dateFormat
					.parse("2008.12.08 10:28:53 EST"));
			examInstance.setMaxScore(4249);
			examInstance.setCandidateScore(2495);
			examInstance.setCertified(false);
			examInstance.setPercentage(38.4);

			examInstanceService.save(examInstance);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(examInstanceService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=examInstanceService.getCount();
			ExamInstance examInstance = (ExamInstance)examInstanceTestDataFactory.loadOneRecord();					
			examInstanceService.delete(examInstance);
			newCount=examInstanceService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			ExamInstance examInstance = examInstanceService
					.load(examInstanceInstance.getId());
			assertNotNull(examInstance.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<ExamInstance> examInstances = examInstanceService
					.searchByExample(examInstanceInstance);
			assertTrue(!examInstances.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

	public void testFindCandidateScore() {

		//ExamInstance examInstance  = 0;

		//Integer retInteger = examInstanceService.findCandidateScore(examInstance);
	}

	public void testFindMaxScore() {

		//ExamInstance examInstance  = 0;

		//Integer retInteger = examInstanceService.findMaxScore(examInstance);
	}

}
