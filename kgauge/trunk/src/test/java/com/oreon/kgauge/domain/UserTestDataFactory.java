package com.oreon.kgauge.domain;

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

import com.oreon.kgauge.service.UserService;

@Transactional
public class UserTestDataFactory extends AbstractTestDataFactory<User> {

	private List<User> users = new ArrayList<User>();

	private static final Logger logger = Logger
			.getLogger(UserTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void register(User user) {
		users.add(user);
	}

	public User createUserOne() {
		User user = new User();

		try {

			user.setUsername("epsilon67096");
			user.setPassword("Eric");
			user.setEnabled(false);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserTwo() {
		User user = new User();

		try {

			user.setUsername("Wilson4801");
			user.setPassword("Wilson");
			user.setEnabled(false);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserThree() {
		User user = new User();

		try {

			user.setUsername("Eric36577");
			user.setPassword("delta");
			user.setEnabled(false);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserFour() {
		User user = new User();

		try {

			user.setUsername("delta7559");
			user.setPassword("John");
			user.setEnabled(true);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserFive() {
		User user = new User();

		try {

			user.setUsername("Wilson58785");
			user.setPassword("alpha");
			user.setEnabled(false);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User loadOneRecord() {
		List<User> users = userService.loadAll();

		if (users.isEmpty()) {
			persistAll();
			users = userService.loadAll();
		}

		return users.get(new Random().nextInt(users.size()));
	}

	public List<User> getAllAsList() {

		if (users.isEmpty()) {

			createUserOne();
			createUserTwo();
			createUserThree();
			createUserFour();
			createUserFive();

		}

		return users;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (User user : users) {
			try {
				userService.save(user);
			} catch (BusinessException be) {
				logger.warn(" User " + user.getDisplayName()
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
			User user = createRandomUser();
			userService.save(user);
		}
	}

	public User createRandomUser() {
		User user = new User();

		user.setUsername((String) RandomValueGeneratorFactory
				.createInstance("String"));
		user.setPassword((String) RandomValueGeneratorFactory
				.createInstance("String"));
		user.setEnabled((Boolean) RandomValueGeneratorFactory
				.createInstance("boolean"));

		return user;
	}

}
