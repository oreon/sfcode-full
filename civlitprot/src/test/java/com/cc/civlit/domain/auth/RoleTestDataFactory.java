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

import com.cc.civlit.domain.service.RoleService;

@Transactional
public class RoleTestDataFactory extends AbstractTestDataFactory<Role> {

	private List<Role> roles = new ArrayList<Role>();

	private static final Logger logger = Logger
			.getLogger(RoleTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void register(Role role) {
		roles.add(role);
	}

	public Role createRoleOne() {
		Role role = new Role();

		try {

			role.setName("Eric69361");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public Role createRoleTwo() {
		Role role = new Role();

		try {

			role.setName("gamma14102");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public Role createRoleThree() {
		Role role = new Role();

		try {

			role.setName("Malissa84524");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public Role createRoleFour() {
		Role role = new Role();

		try {

			role.setName("Malissa50226");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public Role createRoleFive() {
		Role role = new Role();

		try {

			role.setName("beta59420");

			register(role);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return role;
	}

	public Role loadOneRecord() {
		List<Role> roles = roleService.loadAll();

		if (roles.isEmpty()) {
			persistAll();
			roles = roleService.loadAll();
		}

		return roles.get(new Random().nextInt(roles.size()));
	}

	public List<Role> getAllAsList() {

		if (roles.isEmpty()) {

			createRoleOne();
			createRoleTwo();
			createRoleThree();
			createRoleFour();
			createRoleFive();

		}

		return roles;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Role role : roles) {
			try {
				roleService.save(role);
			} catch (BusinessException be) {
				logger.warn(" Role " + role.getDisplayName()
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
			Role role = createRandomRole();
			roleService.save(role);
		}
	}

	public Role createRandomRole() {
		Role role = new Role();

		role.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return role;
	}

}
