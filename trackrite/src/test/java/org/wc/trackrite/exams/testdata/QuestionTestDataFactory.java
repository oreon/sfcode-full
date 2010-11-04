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

public class QuestionTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.exams.Question> {

	private List<org.wc.trackrite.exams.Question> questions = new ArrayList<org.wc.trackrite.exams.Question>();

	private static final Logger logger = Logger
			.getLogger(QuestionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.exams.QuestionAction questionAction;

	org.wc.trackrite.exams.testdata.ExamTestDataFactory examTestDataFactory = new org.wc.trackrite.exams.testdata.ExamTestDataFactory();

	public void register(org.wc.trackrite.exams.Question question) {
		questions.add(question);
	}

	public org.wc.trackrite.exams.Question createQuestionOne() {
		org.wc.trackrite.exams.Question question = new org.wc.trackrite.exams.Question();

		try {

			question.setText("theta");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.exams.Question createQuestionTwo() {
		org.wc.trackrite.exams.Question question = new org.wc.trackrite.exams.Question();

		try {

			question.setText("beta");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.exams.Question createQuestionThree() {
		org.wc.trackrite.exams.Question question = new org.wc.trackrite.exams.Question();

		try {

			question.setText("Eric");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.exams.Question createQuestionFour() {
		org.wc.trackrite.exams.Question question = new org.wc.trackrite.exams.Question();

		try {

			question.setText("Mark");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.exams.Question createQuestionFive() {
		org.wc.trackrite.exams.Question question = new org.wc.trackrite.exams.Question();

		try {

			question.setText("theta");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public List<org.wc.trackrite.exams.Question> createAll() {
		createQuestionOne();
		createQuestionTwo();
		createQuestionThree();
		createQuestionFour();
		createQuestionFive();

		return questions;
	}

	@Override
	public List<org.wc.trackrite.exams.Question> getListOfRecords() {
		return questions;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.exams.Question e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.exams.Question question : questions) {
			persist(question);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new QuestionTestDataFactory().persistAll();
	}

}
