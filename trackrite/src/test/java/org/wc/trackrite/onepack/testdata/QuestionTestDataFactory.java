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

public class QuestionTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.onepack.Question> {

	private List<org.wc.trackrite.onepack.Question> questions = new ArrayList<org.wc.trackrite.onepack.Question>();

	private static final Logger logger = Logger
			.getLogger(QuestionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.onepack.QuestionAction questionAction;

	org.wc.trackrite.onepack.testdata.ExamTestDataFactory examTestDataFactory = new org.wc.trackrite.onepack.testdata.ExamTestDataFactory();

	public void register(org.wc.trackrite.onepack.Question question) {
		questions.add(question);
	}

	public org.wc.trackrite.onepack.Question createQuestionOne() {
		org.wc.trackrite.onepack.Question question = new org.wc.trackrite.onepack.Question();

		try {

			question.setText("alpha");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.onepack.Question createQuestionTwo() {
		org.wc.trackrite.onepack.Question question = new org.wc.trackrite.onepack.Question();

		try {

			question.setText("Mark");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.onepack.Question createQuestionThree() {
		org.wc.trackrite.onepack.Question question = new org.wc.trackrite.onepack.Question();

		try {

			question.setText("gamma");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.onepack.Question createQuestionFour() {
		org.wc.trackrite.onepack.Question question = new org.wc.trackrite.onepack.Question();

		try {

			question.setText("epsilon");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.onepack.Question createQuestionFive() {
		org.wc.trackrite.onepack.Question question = new org.wc.trackrite.onepack.Question();

		try {

			question.setText("Malissa");

			question.setExam(examTestDataFactory.getRandomRecord());

			register(question);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return question;
	}

	public org.wc.trackrite.onepack.Question getRandomRecord() {

		if (questions.isEmpty()) {
			createAll();
		}

		return questions.get(new Random().nextInt(questions.size()));
	}

	public List<org.wc.trackrite.onepack.Question> createAll() {
		createQuestionOne();
		createQuestionTwo();
		createQuestionThree();
		createQuestionFour();
		createQuestionFive();

		return questions;
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.onepack.Question question : questions) {
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
