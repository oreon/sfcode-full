package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.kgauge.service.AnswerChoiceService;

@Transactional
public class AnswerChoiceTestDataFactory
		extends
			AbstractTestDataFactory<AnswerChoice> {

	private List<AnswerChoice> answerChoices = new ArrayList<AnswerChoice>();

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	AnswerChoiceService answerChoiceService;

	public AnswerChoiceService getAnswerChoiceService() {
		return answerChoiceService;
	}

	public void setAnswerChoiceService(AnswerChoiceService answerChoiceService) {
		this.answerChoiceService = answerChoiceService;
	}

	public void register(AnswerChoice answerChoice) {
		answerChoices.add(answerChoice);
	}

	public AnswerChoice createAnswerChoiceOne() {
		AnswerChoice answerChoice = new AnswerChoice();

		try {

			answerChoice.setAnswerText("theta");
			answerChoice.setScore(8936);

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answerChoice
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answerChoice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answerChoice;
	}

	public AnswerChoice createAnswerChoiceTwo() {
		AnswerChoice answerChoice = new AnswerChoice();

		try {

			answerChoice.setAnswerText("pi");
			answerChoice.setScore(5173);

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answerChoice
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answerChoice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answerChoice;
	}

	public AnswerChoice createAnswerChoiceThree() {
		AnswerChoice answerChoice = new AnswerChoice();

		try {

			answerChoice.setAnswerText("delta");
			answerChoice.setScore(7480);

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answerChoice
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answerChoice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answerChoice;
	}

	public AnswerChoice createAnswerChoiceFour() {
		AnswerChoice answerChoice = new AnswerChoice();

		try {

			answerChoice.setAnswerText("gamma");
			answerChoice.setScore(3867);

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answerChoice
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answerChoice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answerChoice;
	}

	public AnswerChoice createAnswerChoiceFive() {
		AnswerChoice answerChoice = new AnswerChoice();

		try {

			answerChoice.setAnswerText("zeta");
			answerChoice.setScore(6625);

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answerChoice
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			register(answerChoice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answerChoice;
	}

	public AnswerChoice loadOneRecord() {
		List<AnswerChoice> answerChoices = answerChoiceService.loadAll();

		if (answerChoices.isEmpty()) {
			persistAll();
			answerChoices = answerChoiceService.loadAll();
		}

		return answerChoices.get(new Random().nextInt(answerChoices.size()));
	}

	public List<AnswerChoice> getAllAsList() {

		if (answerChoices.isEmpty()) {

			createAnswerChoiceOne();
			createAnswerChoiceTwo();
			createAnswerChoiceThree();
			createAnswerChoiceFour();
			createAnswerChoiceFive();

		}

		return answerChoices;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (AnswerChoice answerChoice : answerChoices) {
			answerChoiceService.save(answerChoice);
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
			AnswerChoice answerChoice = createRandomAnswerChoice();
			answerChoiceService.save(answerChoice);
		}
	}

	public AnswerChoice createRandomAnswerChoice() {
		AnswerChoice answerChoice = new AnswerChoice();

		answerChoice.setAnswerText((String) RandomValueGeneratorFactory
				.createInstance("String"));
		answerChoice.setScore((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));

		TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("questionTestDataFactory");

		answerChoice
				.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
						.loadOneRecord());

		return answerChoice;
	}

}
