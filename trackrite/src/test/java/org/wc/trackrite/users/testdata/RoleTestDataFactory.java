package org.wc.trackrite.users.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.seam.action.AbstractTestDataFactory; //import org.witchcraft.model.support.testing.AbstractTestDataFactory;
//import org.witchcraft.model.support.testing.TestDataFactory;
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

			role.setName("Mark");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleTwo() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("Lavendar");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleThree() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("gamma");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleFour() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("beta");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role createRoleFive() {
		org.wc.trackrite.users.Role role = new org.wc.trackrite.users.Role();

		try {

			role.setName("Eric");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public org.wc.trackrite.users.Role getRandomRecord() {

		if (roles.isEmpty()) {
			createAll();
		}

		return roles.get(new Random().nextInt(roles.size()));
	}

	public List<org.wc.trackrite.users.Role> createAll() {
		createRoleOne();
		createRoleTwo();
		createRoleThree();
		createRoleFour();
		createRoleFive();

		return roles;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (roleAction == null)
			roleAction = (com.nas.recovery.web.action.users.RoleAction) Component
					.getInstance("roleAction");

		for (org.wc.trackrite.users.Role role : roles) {
			//try {
			roleAction.setInstance(role);
			roleAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" Role " + role.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new RoleTestDataFactory().persistAll();
	}

}
