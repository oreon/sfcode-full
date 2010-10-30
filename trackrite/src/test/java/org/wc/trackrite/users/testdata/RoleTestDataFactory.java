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

public class RoleTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.users.Role> {

	private List<org.wc.trackrite.users.Role> roles = new ArrayList<org.wc.trackrite.users.Role>();

	private static final Logger logger = Logger
			.getLogger(RoleTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.users.RoleAction roleAction;

	public void register(org.wc.trackrite.users.Role role) {
		roles.add(role);
	}

	public org.wc.trackrite.users.Role createRoleOne() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("pi");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleTwo() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("Wilson");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleThree() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("pi");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleFour() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("Mark");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleFive() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("Mark");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public List<org.wc.trackrite.users.Role> createAll() {
		createRoleOne();
		createRoleTwo();
		createRoleThree();
		createRoleFour();
		createRoleFive();

		return roles;
	}

	@Override
	public List<org.wc.trackrite.users.Role> getListOfRecords() {
		return roles;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.users.Role e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.users.Role role : roles) {
			persist(role);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new RoleTestDataFactory().persistAll();
	}

}
