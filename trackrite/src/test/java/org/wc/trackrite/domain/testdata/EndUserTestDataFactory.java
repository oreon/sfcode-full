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

			endUser.setFirstName("gamma");

			endUser.setLastName("delta");

			endUser.getUser().setUserName("delta");

			endUser.getUser().setPassword("beta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Mark");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.06 04:03:25 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserTwo() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("alpha");

			endUser.setLastName("gamma");

			endUser.getUser().setUserName("pi");

			endUser.getUser().setPassword("Wilson");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("John");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.12.28 12:07:50 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserThree() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("gamma");

			endUser.setLastName("alpha");

			endUser.getUser().setUserName("pi");

			endUser.getUser().setPassword("Wilson");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.02 22:56:12 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFour() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("delta");

			endUser.setLastName("gamma");

			endUser.getUser().setUserName("Lavendar");

			endUser.getUser().setPassword("Mark");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Mark");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.20 10:09:32 EST"));

			register(endUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return endUser;
	}

	public org.wc.trackrite.domain.EndUser createEndUserFive() {
		org.wc.trackrite.domain.EndUser endUser = new org.wc.trackrite.domain.EndUser();

		try {

			endUser.setFirstName("alpha");

			endUser.setLastName("gamma");

			endUser.getUser().setUserName("zeta");

			endUser.getUser().setPassword("John");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("beta");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2011.01.05 00:03:25 EST"));

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
