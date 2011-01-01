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

			endUser.setFirstName("delta");

			endUser.setLastName("theta");

			endUser.getUser().setUserName("Wilson");

			endUser.getUser().setPassword("Wilson");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("epsilon");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.26 04:39:58 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserTwo() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("pi");

			endUser.setLastName("gamma");

			endUser.getUser().setUserName("Eric");

			endUser.getUser().setPassword("Mark");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("theta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.14 23:51:07 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserThree() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("John");

			endUser.setLastName("beta");

			endUser.getUser().setUserName("John");

			endUser.getUser().setPassword("epsilon");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("delta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.12.17 01:41:07 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFour() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("John");

			endUser.setLastName("delta");

			endUser.getUser().setUserName("delta");

			endUser.getUser().setPassword("gamma");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Mark");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.12.14 05:47:47 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFive() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("epsilon");

			endUser.setLastName("alpha");

			endUser.getUser().setUserName("John");

			endUser.getUser().setPassword("Wilson");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("beta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.12.16 05:01:40 EST"));

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
