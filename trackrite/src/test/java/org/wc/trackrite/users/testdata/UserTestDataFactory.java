package org.wc.trackrite.users.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

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

			user.setUserName("Lavendar");

			user.setPassword("gamma");

			user.setEnabled(true);

			user.setEmail("John");

			user.setLastLogin(dateFormat.parse("2010.10.27 07:09:36 EDT"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserTwo() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("alpha");

			user.setPassword("Wilson");

			user.setEnabled(true);

			user.setEmail("Wilson");

			user.setLastLogin(dateFormat.parse("2010.11.07 04:07:22 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserThree() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("theta");

			user.setPassword("zeta");

			user.setEnabled(true);

			user.setEmail("epsilon");

			user.setLastLogin(dateFormat.parse("2010.10.11 12:50:42 EDT"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFour() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("theta");

			user.setPassword("gamma");

			user.setEnabled(true);

			user.setEmail("Lavendar");

			user.setLastLogin(dateFormat.parse("2010.10.23 18:36:49 EDT"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFive() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("John");

			user.setPassword("Lavendar");

			user.setEnabled(true);

			user.setEmail("John");

			user.setLastLogin(dateFormat.parse("2010.10.08 00:01:15 EDT"));

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
		init();
		createAll();

		for (org.wc.trackrite.users.User user : users) {
			persist(user);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new UserTestDataFactory().persistAll();
	}

}
