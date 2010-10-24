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

			endUser.setFirstName("theta");

			endUser.setLastName("delta");

			endUser.getUser().setUserName("beta");

			endUser.getUser().setPassword("Eric");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.03 01:20:52 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserTwo() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("epsilon");

			endUser.setLastName("Lavendar");

			endUser.getUser().setUserName("gamma");

			endUser.getUser().setPassword("pi");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("theta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.26 03:43:40 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserThree() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("Malissa");

			endUser.setLastName("alpha");

			endUser.getUser().setUserName("Mark");

			endUser.getUser().setPassword("Mark");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.09.30 10:18:38 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFour() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("Mark");

			endUser.setLastName("pi");

			endUser.getUser().setUserName("zeta");

			endUser.getUser().setPassword("alpha");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("pi");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.09.29 03:24:12 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFive() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("gamma");

			endUser.setLastName("beta");

			endUser.getUser().setUserName("zeta");

			endUser.getUser().setPassword("alpha");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("gamma");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.12 02:04:45 EST"));

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
