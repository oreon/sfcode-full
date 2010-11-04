package org.wc.trackrite.exams.testdata;

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
			AbstractTestDataFactory<org.wc.trackrite.exams.Answer> {

	private List<org.wc.trackrite.exams.Answer> answers = new ArrayList<org.wc.trackrite.exams.Answer>();

	private static final Logger logger = Logger
			.getLogger(AnswerTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.exams.AnswerAction answerAction;

	org.wc.trackrite.exams.testdata.ChoiceTestDataFactory choiceTestDataFactory = new org.wc.trackrite.exams.testdata.ChoiceTestDataFactory();

	org.wc.trackrite.exams.testdata.ExamInstanceTestDataFactory examInstanceTestDataFactory = new org.wc.trackrite.exams.testdata.ExamInstanceTestDataFactory();

	public void register(org.wc.trackrite.exams.Answer answer) {
		answers.add(answer);
	}

	public org.wc.trackrite.exams.Answer createAnswerOne() {
		org.wc.trackrite.exams.Answer answer = new org.wc.trackrite.exams.Answer();

		try {

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.exams.Answer createAnswerTwo() {
		org.wc.trackrite.exams.Answer answer = new org.wc.trackrite.exams.Answer();

		try {

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.exams.Answer createAnswerThree() {
		org.wc.trackrite.exams.Answer answer = new org.wc.trackrite.exams.Answer();

		try {

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.exams.Answer createAnswerFour() {
		org.wc.trackrite.exams.Answer answer = new org.wc.trackrite.exams.Answer();

		try {

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public org.wc.trackrite.exams.Answer createAnswerFive() {
		org.wc.trackrite.exams.Answer answer = new org.wc.trackrite.exams.Answer();

		try {

			answer.setChoice(choiceTestDataFactory.getRandomRecord());

			answer.setExamInstance(examInstanceTestDataFactory
					.getRandomRecord());

			register(answer);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return answer;
	}

	public List<org.wc.trackrite.exams.Answer> createAll() {
		createAnswerOne();
		createAnswerTwo();
		createAnswerThree();
		createAnswerFour();
		createAnswerFive();

		return answers;
	}

	@Override
	public List<org.wc.trackrite.exams.Answer> getListOfRecords() {
		return answers;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.exams.Answer e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.exams.Answer answer : answers) {
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
