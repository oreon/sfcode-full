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

			user.setUserName("alpha");

			user.setPassword("Mark");

			user.setEnabled(true);

			user.setEmail("delta");

			user.setLastLogin(dateFormat.parse("2010.10.21 17:29:45 EDT"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserTwo() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("Wilson");

			user.setPassword("Malissa");

			user.setEnabled(true);

			user.setEmail("gamma");

			user.setLastLogin(dateFormat.parse("2010.10.26 17:51:23 EDT"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserThree() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("Eric");

			user.setPassword("Eric");

			user.setEnabled(true);

			user.setEmail("pi");

			user.setLastLogin(dateFormat.parse("2010.10.24 12:07:30 EDT"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFour() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("Mark");

			user.setPassword("Mark");

			user.setEnabled(true);

			user.setEmail("delta");

			user.setLastLogin(dateFormat.parse("2010.10.19 22:53:38 EDT"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFive() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("Malissa");

			user.setPassword("beta");

			user.setEnabled(true);

			user.setEmail("Malissa");

			user.setLastLogin(dateFormat.parse("2010.11.22 17:03:38 EST"));

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
