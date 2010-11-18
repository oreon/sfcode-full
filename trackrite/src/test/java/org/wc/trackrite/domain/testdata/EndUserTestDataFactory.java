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

			endUser.setFirstName("pi");

			endUser.setLastName("delta");

			endUser.getUser().setUserName("beta");

			endUser.getUser().setPassword("delta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.31 05:25:24 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserTwo() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("John");

			endUser.setLastName("zeta");

			endUser.getUser().setUserName("Wilson");

			endUser.getUser().setPassword("zeta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("John");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.28 08:59:53 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserThree() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("beta");

			endUser.setLastName("gamma");

			endUser.getUser().setUserName("zeta");

			endUser.getUser().setPassword("delta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.31 04:44:51 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFour() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("gamma");

			endUser.setLastName("Lavendar");

			endUser.getUser().setUserName("delta");

			endUser.getUser().setPassword("gamma");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("zeta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.17 21:38:44 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFive() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("pi");

			endUser.setLastName("Malissa");

			endUser.getUser().setUserName("John");

			endUser.getUser().setPassword("pi");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("gamma");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.26 22:17:39 EDT"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public List<org.wc.trackrite.domain.EndUser> createAll() {
		createEndUserOne();
		createEndUserTwo();
		createEndUserThree();
		createEndUserFour();
		createEndUserFive();

		return endUsers;
	}

	@Override
	public List<org.wc.trackrite.domain.EndUser> getListOfRecords() {
		return endUsers;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.domain.EndUser e ";
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