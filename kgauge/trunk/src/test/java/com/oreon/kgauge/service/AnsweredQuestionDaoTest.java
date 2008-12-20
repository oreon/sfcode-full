package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.AnsweredQuestion;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class AnsweredQuestionDaoTest extends AbstractJpaTests {

	protected AnsweredQuestion answeredQuestionInstance = new AnsweredQuestion();

	protected AnsweredQuestionService answeredQuestionService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setAnsweredQuestionService(
			AnsweredQuestionService answeredQuestionService) {
		this.answeredQuestionService = answeredQuestionService;
	}

	protected TestDataFactory answeredQuestionTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("answeredQuestionTestDataFactory");

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

			answeredQuestionInstance.setIsCorrect(true);

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answeredQuestionInstance
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			TestDataFactory examInstanceTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examInstanceTestDataFactory");

			answeredQuestionInstance
					.setExamInstance((com.oreon.kgauge.domain.ExamInstance) examInstanceTestDataFactory
							.loadOneRecord());

			TestDataFactory answerChoiceTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("answerChoiceTestDataFactory");

			answeredQuestionInstance
					.setAnswerChoice((com.oreon.kgauge.domain.AnswerChoice) answerChoiceTestDataFactory
							.loadOneRecord());

			answeredQuestionService.save(answeredQuestionInstance);
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
			AnsweredQuestion answeredQuestion = new AnsweredQuestion();

			try {

				answeredQuestion.setIsCorrect(false);

				TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("questionTestDataFactory");

				answeredQuestion
						.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
								.loadOneRecord());

				TestDataFactory examInstanceTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("examInstanceTestDataFactory");

				answeredQuestion
						.setExamInstance((com.oreon.kgauge.domain.ExamInstance) examInstanceTestDataFactory
								.loadOneRecord());

				TestDataFactory answerChoiceTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("answerChoiceTestDataFactory");

				answeredQuestion
						.setAnswerChoice((com.oreon.kgauge.domain.AnswerChoice) answerChoiceTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			answeredQuestionService.save(answeredQuestion);
			assertNotNull(answeredQuestion.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			AnsweredQuestion answeredQuestion = (AnsweredQuestion) answeredQuestionTestDataFactory
					.loadOneRecord();

			answeredQuestion.setIsCorrect(true);

			answeredQuestionService.save(answeredQuestion);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(answeredQuestionService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=answeredQuestionService.getCount();
			AnsweredQuestion answeredQuestion = (AnsweredQuestion)answeredQuestionTestDataFactory.loadOneRecord();					
			answeredQuestionService.delete(answeredQuestion);
			newCount=answeredQuestionService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			AnsweredQuestion answeredQuestion = answeredQuestionService
					.load(answeredQuestionInstance.getId());
			assertNotNull(answeredQuestion.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<AnsweredQuestion> answeredQuestions = answeredQuestionService
					.searchByExample(answeredQuestionInstance);
			assertTrue(!answeredQuestions.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
