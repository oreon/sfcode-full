package org.wc.trackrite.onepack.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.seam.action.AbstractTestDataFactory; //import org.witchcraft.model.support.testing.AbstractTestDataFactory;
//import org.witchcraft.model.support.testing.TestDataFactory;
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

			choice.setText("pi");

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

			choice.setText("zeta");

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

			choice.setText("John");

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

			choice.setText("Lavendar");

			choice.setQuestion(questionTestDataFactory.getRandomRecord());

			register(choice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return choice;
	}

	public org.wc.trackrite.onepack.Choice getRandomRecord() {

		if (choices.isEmpty()) {
			createAll();
		}

		return choices.get(new Random().nextInt(choices.size()));
	}

	public List<org.wc.trackrite.onepack.Choice> createAll() {
		createChoiceOne();
		createChoiceTwo();
		createChoiceThree();
		createChoiceFour();
		createChoiceFive();

		return choices;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (choiceAction == null)
			choiceAction = (com.nas.recovery.web.action.onepack.ChoiceAction) Component
					.getInstance("choiceAction");

		for (org.wc.trackrite.onepack.Choice choice : choices) {
			//try {
			choiceAction.setInstance(choice);
			choiceAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" Choice " + choice.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ChoiceTestDataFactory().persistAll();
	}

}
