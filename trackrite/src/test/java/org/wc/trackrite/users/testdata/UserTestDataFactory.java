package org.wc.trackrite.users.testdata;

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

public class UserTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.users.User> {

	private List<org.wc.trackrite.users.User> users = new ArrayList<org.wc.trackrite.users.User>();

	private static final Logger logger = Logger
			.getLogger(UserTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.users.UserAction userAction;

	public void register(org.wc.trackrite.users.User user) {
		users.add(user);
	}

	public org.wc.trackrite.users.User createUserOne() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("epsilon");

			user.setPassword("Lavendar");

			user.setEnabled(false);

			user.setEmail("pi");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserTwo() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("Malissa");

			user.setPassword("alpha");

			user.setEnabled(true);

			user.setEmail("delta");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserThree() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("alpha");

			user.setPassword("Mark");

			user.setEnabled(true);

			user.setEmail("alpha");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFour() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("Lavendar");

			user.setPassword("Wilson");

			user.setEnabled(true);

			user.setEmail("theta");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFive() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("epsilon");

			user.setPassword("Eric");

			user.setEnabled(false);

			user.setEmail("pi");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User getRandomRecord() {

		if (users.isEmpty()) {
			createAll();
		}

		return users.get(new Random().nextInt(users.size()));
	}

	public List<org.wc.trackrite.users.User> createAll() {
		createUserOne();
		createUserTwo();
		createUserThree();
		createUserFour();
		createUserFive();

		return users;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (userAction == null)
			userAction = (com.nas.recovery.web.action.users.UserAction) Component
					.getInstance("userAction");

		for (org.wc.trackrite.users.User user : users) {
			//try {
			userAction.setInstance(user);
			userAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" User " + user.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new UserTestDataFactory().persistAll();
	}

}
