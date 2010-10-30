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

public class ChoiceTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.onepack.Choice> {

	private List<org.wc.trackrite.onepack.Choice> choices = new ArrayList<org.wc.trackrite.onepack.Choice>();

	private static final Logger logger = Logger
			.getLogger(ChoiceTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.onepack.ChoiceAction choiceAction;

	org.wc.trackrite.onepack.testdata.QuestionTestDataFactory questionTestDataFactory = new org.wc.trackrite.onepack.testdata.QuestionTestDataFactory();

	public void register(org.wc.trackrite.onepack.Choice choice) {
		choices.add(choice);
	}

	public org.wc.trackrite.onepack.Choice createChoiceOne() {
		org.wc.trackrite.onepack.Choice choice = new org.wc.trackrite.onepack.Choice();

		try {

			choice.setText("beta");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.onepack.Choice createChoiceTwo() {
		org.wc.trackrite.onepack.Choice choice = new org.wc.trackrite.onepack.Choice();

		try {

			choice.setText("Wilson");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.onepack.Choice createChoiceThree() {
		org.wc.trackrite.onepack.Choice choice = new org.wc.trackrite.onepack.Choice();

		try {

			choice.setText("pi");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.onepack.Choice createChoiceFour() {
		org.wc.trackrite.onepack.Choice choice = new org.wc.trackrite.onepack.Choice();

		try {

			choice.setText("pi");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.onepack.Choice createChoiceFive() {
		org.wc.trackrite.onepack.Choice choice = new org.wc.trackrite.onepack.Choice();

		try {

			choice.setText("gamma");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public List<org.wc.trackrite.onepack.Choice> createAll() {
		createChoiceOne();
		createChoiceTwo();
		createChoiceThree();
		createChoiceFour();
		createChoiceFive();

		return choices;
	}

	@Override
	public List<org.wc.trackrite.onepack.Choice> getListOfRecords() {
		return choices;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.onepack.Choice e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.onepack.Choice choice : choices) {
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
