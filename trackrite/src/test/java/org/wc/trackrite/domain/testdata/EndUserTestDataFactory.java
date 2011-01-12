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

			endUser.setFirstName("Wilson");

			endUser.setLastName("Malissa");

			endUser.getUser().setUserName("Eric");

			endUser.getUser().setPassword("zeta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Wilson");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.12.17 12:18:29 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserTwo() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("Malissa");

			endUser.setLastName("Wilson");

			endUser.getUser().setUserName("zeta");

			endUser.getUser().setPassword("alpha");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.19 05:57:56 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserThree() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("Eric");

			endUser.setLastName("beta");

			endUser.getUser().setUserName("gamma");

			endUser.getUser().setPassword("alpha");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Eric");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.10 08:57:56 EST"));

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

			endUser.setLastName("zeta");

			endUser.getUser().setUserName("John");

			endUser.getUser().setPassword("beta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("alpha");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.13 12:50:43 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFive() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("Wilson");

			endUser.setLastName("John");

			endUser.getUser().setUserName("epsilon");

			endUser.getUser().setPassword("gamma");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Lavendar");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.24 20:16:50 EST"));

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
