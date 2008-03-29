package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.Question;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class QuestionDaoTest extends AbstractJpaTests {

	protected Question questionInstance = new Question();

	protected QuestionService questionService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	protected TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("questionTestDataFactory");

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

			questionInstance.setQuestionText("theta");
			questionInstance
					.setDifficultyLevel(com.oreon.kgauge.domain.DifficultyLevel.L3);

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			questionInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			TestDataFactory sectionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("sectionTestDataFactory");

			questionInstance
					.setSection((com.oreon.kgauge.domain.Section) sectionTestDataFactory
							.loadOneRecord());

			questionService.save(questionInstance);
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
			Question question = new Question();

			try {

				question.setQuestionText("theta");
				question
						.setDifficultyLevel(com.oreon.kgauge.domain.DifficultyLevel.L2);

				TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("examTestDataFactory");

				question
						.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
								.loadOneRecord());

				TestDataFactory sectionTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("sectionTestDataFactory");

				question
						.setSection((com.oreon.kgauge.domain.Section) sectionTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			questionService.save(question);
			assertNotNull(question.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Question question = (Question) questionTestDataFactory
					.loadOneRecord();

			question.setQuestionText("beta");
			question
					.setDifficultyLevel(com.oreon.kgauge.domain.DifficultyLevel.L1);

			questionService.save(question);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(questionService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = questionService.getCount();
			Question question = (Question) questionTestDataFactory
					.loadOneRecord();
			questionService.delete(question);
			newCount = questionService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			Question question = questionService.load(questionInstance.getId());
			assertNotNull(question.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Question> questions = questionService
					.searchByExample(questionInstance);
			assertTrue(!questions.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
