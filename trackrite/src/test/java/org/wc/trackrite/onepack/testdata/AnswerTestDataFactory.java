package org.wc.trackrite.onepack.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class AnswerTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.onepack.Answer> {

	private List<org.wc.trackrite.onepack.Answer> answers = new ArrayList<org.wc.trackrite.onepack.Answer>();

	private static final Logger logger = Logger
			.getLogger(AnswerTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.onepack.AnswerAction answerAction;

	org.wc.trackrite.onepack.testdata.QuestionTestDataFactory questionTestDataFactory = new org.wc.trackrite.onepack.testdata.QuestionTestDataFactory();

	org.wc.trackrite.onepack.testdata.ChoiceTestDataFactory choiceTestDataFactory = new org.wc.trackrite.onepack.testdata.ChoiceTestDataFactory();

	org.wc.trackrite.onepack.testdata.ExamInstanceTestDataFactory examInstanceTestDataFactory = new org.wc.trackrite.onepack.testdata.ExamInstanceTestDataFactory();

	public void register(org.wc.trackrite.onepack.Answer answer) {
		answers.add(answer);
	}

	public org.wc.trackrite.onepack.Answer createAnswerOne() {
		org.wc.trackrite.onepack.Answer answer = new org.wc.trackrite.onepack.Answer();

		try {

			answer.setQuestion(questionTestDataFactory.getRandomRecord());

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.onepack.Answer createAnswerTwo() {
		org.wc.trackrite.onepack.Answer answer = new org.wc.trackrite.onepack.Answer();

		try {

			answer.setQuestion(questionTestDataFactory.getRandomRecord());

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.onepack.Answer createAnswerThree() {
		org.wc.trackrite.onepack.Answer answer = new org.wc.trackrite.onepack.Answer();

		try {

			answer.setQuestion(questionTestDataFactory.getRandomRecord());

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.onepack.Answer createAnswerFour() {
		org.wc.trackrite.onepack.Answer answer = new org.wc.trackrite.onepack.Answer();

		try {

			answer.setQuestion(questionTestDataFactory.getRandomRecord());

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.onepack.Answer createAnswerFive() {
		org.wc.trackrite.onepack.Answer answer = new org.wc.trackrite.onepack.Answer();

		try {

			answer.setQuestion(questionTestDataFactory.getRandomRecord());

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.onepack.Answer getRandomRecord() {

		if (answers.isEmpty()) {
			createAll();
		}

		return answers.get(new Random().nextInt(answers.size()));
	}

	public List<org.wc.trackrite.onepack.Answer> createAll() {
		createAnswerOne();
		createAnswerTwo();
		createAnswerThree();
		createAnswerFour();
		createAnswerFive();

		return answers;
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.onepack.Answer answer : answers) {
			persist(answer);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new AnswerTestDataFactory().persistAll();
	}

}
