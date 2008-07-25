package com.cc.civlit.domain.auth;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.cc.civlit.domain.service.GrantedRoleService;

@Transactional
public class GrantedRoleTestDataFactory
		extends
			AbstractTestDataFactory<GrantedRole> {

	private List<GrantedRole> grantedRoles = new ArrayList<GrantedRole>();

	private static final Logger logger = Logger
			.getLogger(GrantedRoleTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	GrantedRoleService grantedRoleService;

	public GrantedRoleService getGrantedRoleService() {
		return grantedRoleService;
	}

	public void setGrantedRoleService(GrantedRoleService grantedRoleService) {
		this.grantedRoleService = grantedRoleService;
	}

	public void register(GrantedRole grantedRole) {
		grantedRoles.add(grantedRole);
	}

	public GrantedRole createGrantedRoleOne() {
		GrantedRole grantedRole = new GrantedRole();

		try {

			grantedRole.setName("alpha");

			TestDataFactory roleTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("roleTestDataFactory");

			grantedRole
					.setRole((com.cc.civlit.domain.auth.Role) roleTestDataFactory
							.loadOneRecord());

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedRole
					.setUser((com.cc.civlit.domain.auth.User) userTestDataFactory
							.loadOneRecord());

			register(grantedRole);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedRole;
	}

	public GrantedRole createGrantedRoleTwo() {
		GrantedRole grantedRole = new GrantedRole();

		try {

			grantedRole.setName("gamma");

			TestDataFactory roleTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("roleTestDataFactory");

			grantedRole
					.setRole((com.cc.civlit.domain.auth.Role) roleTestDataFactory
							.loadOneRecord());

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedRole
					.setUser((com.cc.civlit.domain.auth.User) userTestDataFactory
							.loadOneRecord());

			register(grantedRole);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedRole;
	}

	public GrantedRole createGrantedRoleThree() {
		GrantedRole grantedRole = new GrantedRole();

		try {

			grantedRole.setName("John");

			TestDataFactory roleTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("roleTestDataFactory");

			grantedRole
					.setRole((com.cc.civlit.domain.auth.Role) roleTestDataFactory
							.loadOneRecord());

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedRole
					.setUser((com.cc.civlit.domain.auth.User) userTestDataFactory
							.loadOneRecord());

			register(grantedRole);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedRole;
	}

	public GrantedRole createGrantedRoleFour() {
		GrantedRole grantedRole = new GrantedRole();

		try {

			grantedRole.setName("alpha");

			TestDataFactory roleTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("roleTestDataFactory");

			grantedRole
					.setRole((com.cc.civlit.domain.auth.Role) roleTestDataFactory
							.loadOneRecord());

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedRole
					.setUser((com.cc.civlit.domain.auth.User) userTestDataFactory
							.loadOneRecord());

			register(grantedRole);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedRole;
	}

	public GrantedRole createGrantedRoleFive() {
		GrantedRole grantedRole = new GrantedRole();

		try {

			grantedRole.setName("zeta");

			TestDataFactory roleTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("roleTestDataFactory");

			grantedRole
					.setRole((com.cc.civlit.domain.auth.Role) roleTestDataFactory
							.loadOneRecord());

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedRole
					.setUser((com.cc.civlit.domain.auth.User) userTestDataFactory
							.loadOneRecord());

			register(grantedRole);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedRole;
	}

	public GrantedRole loadOneRecord() {
		List<GrantedRole> grantedRoles = grantedRoleService.loadAll();

		if (grantedRoles.isEmpty()) {
			persistAll();
			grantedRoles = grantedRoleService.loadAll();
		}

		return grantedRoles.get(new Random().nextInt(grantedRoles.size()));
	}

	public List<GrantedRole> getAllAsList() {

		if (grantedRoles.isEmpty()) {

			createGrantedRoleOne();
			createGrantedRoleTwo();
			createGrantedRoleThree();
			createGrantedRoleFour();
			createGrantedRoleFive();

		}

		return grantedRoles;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (GrantedRole grantedRole : grantedRoles) {
			try {
				grantedRoleService.save(grantedRole);
			} catch (BusinessException be) {
				logger.warn(" GrantedRole " + grantedRole.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			GrantedRole grantedRole = createRandomGrantedRole();
			grantedRoleService.save(grantedRole);
		}
	}

	public GrantedRole createRandomGrantedRole() {
		GrantedRole grantedRole = new GrantedRole();

		grantedRole.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory roleTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("roleTestDataFactory");

		grantedRole
				.setRole((com.cc.civlit.domain.auth.Role) roleTestDataFactory
						.loadOneRecord());

		TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("userTestDataFactory");

		grantedRole
				.setUser((com.cc.civlit.domain.auth.User) userTestDataFactory
						.loadOneRecord());

		return grantedRole;
	}

}
