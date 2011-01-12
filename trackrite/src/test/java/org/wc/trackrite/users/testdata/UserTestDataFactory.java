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

			user.setUserName("Eric");

			user.setPassword("epsilon");

			user.setEnabled(true);

			user.setEmail("epsilon");

			user.setLastLogin(dateFormat.parse("2011.01.16 00:40:11 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserTwo() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("John");

			user.setPassword("Mark");

			user.setEnabled(true);

			user.setEmail("Wilson");

			user.setLastLogin(dateFormat.parse("2010.12.31 19:30:43 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserThree() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("pi");

			user.setPassword("Eric");

			user.setEnabled(true);

			user.setEmail("Wilson");

			user.setLastLogin(dateFormat.parse("2011.01.04 11:55:41 EST"));

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

			user.setEmail("John");

			user.setLastLogin(dateFormat.parse("2011.01.20 05:57:56 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFive() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("pi");

			user.setPassword("Malissa");

			user.setEnabled(true);

			user.setEmail("pi");

			user.setLastLogin(dateFormat.parse("2011.01.17 07:01:16 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public List<org.wc.trackrite.users.User> createAll() {
		createUserOne();
		createUserTwo();
		createUserThree();
		createUserFour();
		createUserFive();

		return users;
	}

	@Override
	public List<org.wc.trackrite.users.User> getListOfRecords() {
		return users;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.users.User e ";
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
