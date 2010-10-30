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

			endUser.setLastName("alpha");

			endUser.getUser().setUserName("delta");

			endUser.getUser().setPassword("John");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("John");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.23 12:18:03 EST"));

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

			endUser.setLastName("gamma");

			endUser.getUser().setUserName("theta");

			endUser.getUser().setPassword("Lavendar");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("epsilon");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.11.16 06:46:24 EST"));

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

			endUser.setLastName("Lavendar");

			endUser.getUser().setUserName("epsilon");

			endUser.getUser().setPassword("beta");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("alpha");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.19 16:04:10 EDT"));

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

			endUser.setLastName("Malissa");

			endUser.getUser().setUserName("zeta");

			endUser.getUser().setPassword("Lavendar");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Eric");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.30 15:23:05 EDT"));

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

			endUser.setLastName("epsilon");

			endUser.getUser().setUserName("Malissa");

			endUser.getUser().setPassword("Mark");

			endUser.getUser().setEnabled(true);

			endUser.getUser().setEmail("Malissa");

			endUser.getUser().setLastLogin(
					dateFormat.parse("2010.10.11 20:26:25 EDT"));

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
