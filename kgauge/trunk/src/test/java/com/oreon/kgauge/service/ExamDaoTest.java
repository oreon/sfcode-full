package com.oreon.kgauge.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.springframework.test.jpa.AbstractJpaTests;
import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.domain.ScoringType;

public class ExamDaoTest extends AbstractJpaTests {

	protected Exam examInstance = new Exam();

	protected ExamService examService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	protected TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("examTestDataFactory");

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

			examInstance.setDescription("delta");
			examInstance.setName("zeta");
			examInstance.setQuestions(5975);
			examInstance.setDuration(5799);
			examInstance.setPrice(11.93);
			examInstance
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreForAllAnswers);
			examInstance
					.setExamStatus(com.oreon.kgauge.domain.ExamStatus.ACTIVE);

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			examInstance
					.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examCreatorTestDataFactory");

			examInstance
					.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
							.loadOneRecord());

			examService.save(examInstance);
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
			Exam exam = new Exam();

			try {

				exam.setDescription("Malissa");
				exam.setName("pi");
				exam.setQuestions(4238);
				exam.setDuration(3754);
				exam.setPrice(70.1);
				exam
						.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreForAllAnswers);
				exam
						.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INCOMPLETE);

				TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("categoryTestDataFactory");

				exam
						.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
								.loadOneRecord());

				TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("examCreatorTestDataFactory");

				exam
						.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			examService.save(exam);
			assertNotNull(exam.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Exam exam = (Exam) examTestDataFactory.loadOneRecord();

			exam.setDescription("zeta");
			exam.setName("Wilson");
			exam.setQuestions(1502);
			exam.setDuration(4290);
			exam.setPrice(82.14);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreOnlyForCorrectAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INACTIVE);

			examService.save(exam);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(examService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {
		
		try {
			long count, newCount, diff = 0;
			count = examService.getCount();
			Exam exam = (Exam) examTestDataFactory.loadOneRecord();
			examService.delete(exam);
			newCount = examService.getCount();
			diff = newCount - count;
			assertEquals(diff, 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			Exam exam = examService.load(examInstance.getId());
			assertNotNull(exam.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Exam> exams = examService.searchByExample(examInstance);
			assertTrue(!exams.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testSearchByExampleAndCriteria() {
		Exam exam = new Exam();
		exam.setScoringStrategy(ScoringType.ScoreOnlyForCorrectAnswers);
		Range range = new Range(5.0, true, 30.0, false, "price");
		List<Range> rangeList = new ArrayList<Range>();
		rangeList.add(range);
		
		try {
			List<Exam> exams = examService.searchByExample(exam, rangeList);
			//Criteria cirteria = examService.createExampleCriteria(exam);
			//cirteria.add(Restrictions.ge("price", 10.0));
			//cirteria.add(Restrictions.between("price", 10.0, null));
			//List<Exam> exams = cirteria.list();
			assertTrue("size of exams list is " + exams.size(), !exams.isEmpty() && exams.size() == 2);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
