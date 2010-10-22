package org.wc.trackrite.domain.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class EndUserTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.domain.EndUser> {

	private List<org.wc.trackrite.domain.EndUser> endUsers = new ArrayList<org.wc.trackrite.domain.EndUser>();

	private static final Logger logger = Logger
			.getLogger(EndUserTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.domain.EndUserAction endUserAction;

	public void register(org.wc.trackrite.domain.EndUser endUser) {
		endUsers.add(endUser);
	}

	public org.wc.trackrite.domain.EndUser createEndUserOne() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("Mark");

			endUser.setLastName("epsilon");

			endUser.getUser().setUserName("theta");

			endUser.getUser().setPassword("Wilson");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("alpha");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.18 07:10:09 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserTwo() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("Mark");

			endUser.setLastName("John");

			endUser.getUser().setUserName("gamma");

			endUser.getUser().setPassword("zeta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.11 12:30:41 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserThree() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("pi");

			endUser.setLastName("Eric");

			endUser.getUser().setUserName("delta");

			endUser.getUser().setPassword("Eric");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Lavendar");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.11 10:10:41 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFour() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("beta");

			endUser.setLastName("delta");

			endUser.getUser().setUserName("Malissa");

			endUser.getUser().setPassword("pi");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("delta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.03 14:29:36 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFive() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("zeta");

			endUser.setLastName("delta");

			endUser.getUser().setUserName("gamma");

			endUser.getUser().setPassword("alpha");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("zeta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.13 14:17:21 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser getRandomRecord() {

		if (endUsers.isEmpty()) {
			createAll();
		}

		return endUsers.get(new Random().nextInt(endUsers.size()));
	}

	public List<org.wc.trackrite.domain.EndUser> createAll() {
		createEndUserOne();
		createEndUserTwo();
		createEndUserThree();
		createEndUserFour();
		createEndUserFive();

		return endUsers;
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.domain.EndUser endUser : endUsers) {
			persist(endUser);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new EndUserTestDataFactory().persistAll();
	}

}
