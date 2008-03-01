package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.oreon.kgauge.service.AnsweredQuestionService;

@Transactional
public class AnsweredQuestionTestDataFactory
		extends
			AbstractTestDataFactory<AnsweredQuestion> {

	private List<AnsweredQuestion> answeredQuestions = new ArrayList<AnsweredQuestion>();

	private static final Logger logger = Logger
			.getLogger(AnsweredQuestionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	AnsweredQuestionService answeredQuestionService;

	public AnsweredQuestionService getAnsweredQuestionService() {
		return answeredQuestionService;
	}

	public void setAnsweredQuestionService(
			AnsweredQuestionService answeredQuestionService) {
		this.answeredQuestionService = answeredQuestionService;
	}

	public void register(AnsweredQuestion answeredQuestion) {
		answeredQuestions.add(answeredQuestion);
	}

	public AnsweredQuestion createAnsweredQuestionOne() {
		AnsweredQuestion answeredQuestion = new AnsweredQuestion();

		try {

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answeredQuestion
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answeredQuestion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answeredQuestion;
	}

	public AnsweredQuestion createAnsweredQuestionTwo() {
		AnsweredQuestion answeredQuestion = new AnsweredQuestion();

		try {

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answeredQuestion
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answeredQuestion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answeredQuestion;
	}

	public AnsweredQuestion createAnsweredQuestionThree() {
		AnsweredQuestion answeredQuestion = new AnsweredQuestion();

		try {

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answeredQuestion
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answeredQuestion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answeredQuestion;
	}

	public AnsweredQuestion createAnsweredQuestionFour() {
		AnsweredQuestion answeredQuestion = new AnsweredQuestion();

		try {

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answeredQuestion
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answeredQuestion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answeredQuestion;
	}

	public AnsweredQuestion createAnsweredQuestionFive() {
		AnsweredQuestion answeredQuestion = new AnsweredQuestion();

		try {

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answeredQuestion
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answeredQuestion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answeredQuestion;
	}

	public AnsweredQuestion loadOneRecord() {
		List<AnsweredQuestion> answeredQuestions = answeredQuestionService
				.loadAll();

		if (answeredQuestions.isEmpty()) {
			persistAll();
			answeredQuestions = answeredQuestionService.loadAll();
		}

		return answeredQuestions.get(new Random().nextInt(answeredQuestions
				.size()));
	}

	public List<AnsweredQuestion> getAllAsList() {

		if (answeredQuestions.isEmpty()) {

			createAnsweredQuestionOne();
			createAnsweredQuestionTwo();
			createAnsweredQuestionThree();
			createAnsweredQuestionFour();
			createAnsweredQuestionFive();

		}

		return answeredQuestions;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (AnsweredQuestion answeredQuestion : answeredQuestions) {
			try {
				answeredQuestionService.save(answeredQuestion);
			} catch (BusinessException be) {
				logger.warn(" AnsweredQuestion "
						+ answeredQuestion.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			AnsweredQuestion answeredQuestion = createRandomAnsweredQuestion();
			answeredQuestionService.save(answeredQuestion);
		}
	}

	public AnsweredQuestion createRandomAnsweredQuestion() {
		AnsweredQuestion answeredQuestion = new AnsweredQuestion();

		TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("questionTestDataFactory");

		answeredQuestion
				.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
						.loadOneRecord());

		return answeredQuestion;
	}

}
