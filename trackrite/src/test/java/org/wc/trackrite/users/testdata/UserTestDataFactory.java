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

			user.setPassword("Eric");

			user.setEnabled(true);

			user.setEmail("zeta");

			user.setLastLogin(dateFormat.parse("2011.01.31 08:36:45 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserTwo() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("theta");

			user.setPassword("John");

			user.setEnabled(true);

			user.setEmail("zeta");

			user.setLastLogin(dateFormat.parse("2010.12.24 16:15:40 EST"));

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

			user.setPassword("delta");

			user.setEnabled(true);

			user.setEmail("zeta");

			user.setLastLogin(dateFormat.parse("2011.01.01 14:50:05 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFour() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("John");

			user.setPassword("Lavendar");

			user.setEnabled(true);

			user.setEmail("Mark");

			user.setLastLogin(dateFormat.parse("2011.01.11 07:19:32 EST"));

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public org.wc.trackrite.users.User createUserFive() {
		org.wc.trackrite.users.User user = new org.wc.trackrite.users.User();

		try {

			user.setUserName("theta");

			user.setPassword("Malissa");

			user.setEnabled(true);

			user.setEmail("beta");

			user.setLastLogin(dateFormat.parse("2010.12.26 08:21:10 EST"));

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
