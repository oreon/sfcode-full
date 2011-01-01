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

public class ChoiceTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.exams.Choice> {

	private List<org.wc.trackrite.exams.Choice> choices = new ArrayList<org.wc.trackrite.exams.Choice>();

	private static final Logger logger = Logger
			.getLogger(ChoiceTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.exams.ChoiceAction choiceAction;

	org.wc.trackrite.exams.testdata.QuestionTestDataFactory questionTestDataFactory = new org.wc.trackrite.exams.testdata.QuestionTestDataFactory();

	public void register(org.wc.trackrite.exams.Choice choice) {
		choices.add(choice);
	}

	public org.wc.trackrite.exams.Choice createChoiceOne() {
		org.wc.trackrite.exams.Choice choice = new org.wc.trackrite.exams.Choice();

		try {

			choice.setText("John");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.exams.Choice createChoiceTwo() {
		org.wc.trackrite.exams.Choice choice = new org.wc.trackrite.exams.Choice();

		try {

			choice.setText("Lavendar");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.exams.Choice createChoiceThree() {
		org.wc.trackrite.exams.Choice choice = new org.wc.trackrite.exams.Choice();

		try {

			choice.setText("Malissa");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.exams.Choice createChoiceFour() {
		org.wc.trackrite.exams.Choice choice = new org.wc.trackrite.exams.Choice();

		try {

			choice.setText("alpha");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.exams.Choice createChoiceFive() {
		org.wc.trackrite.exams.Choice choice = new org.wc.trackrite.exams.Choice();

		try {

			choice.setText("delta");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public List<org.wc.trackrite.exams.Choice> createAll() {
		createChoiceOne();
		createChoiceTwo();
		createChoiceThree();
		createChoiceFour();
		createChoiceFive();

		return choices;
	}

	@Override
	public List<org.wc.trackrite.exams.Choice> getListOfRecords() {
		return choices;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.exams.Choice e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.exams.Choice choice : choices) {
			persist(choice);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ChoiceTestDataFactory().persistAll();
	}

}
