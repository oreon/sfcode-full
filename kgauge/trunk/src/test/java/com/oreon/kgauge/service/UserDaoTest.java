package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.User;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class UserDaoTest extends AbstractJpaTests {

	protected User userInstance = new User();

	protected UserService userService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	protected TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("userTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			userInstance.setUserName("theta4501");
			userInstance.setPassword("gamma");
			userInstance.setEnabled(false);

			userService.save(userInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			User user = new User();

			try {

				user.setUserName("epsilon99297");
				user.setPassword("Lavendar");
				user.setEnabled(true);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			userService.save(user);
			assertNotNull(user.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			User user = (User) userTestDataFactory.loadOneRecord();

			user.setUserName("John91351");
			user.setPassword("epsilon");
			user.setEnabled(true);

			userService.save(user);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(userService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {
		long count, newCount, diff = 0;
		count = userService.getCount();
		User user = (User) userTestDataFactory.loadOneRecord();
		userService.delete(user);
		newCount = userService.getCount();
		diff = newCount - count;
		try {
			assertEquals(diff, 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			User user = userService.load(userInstance.getId());
			assertNotNull(user.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByUserName() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a User with userName ", userService
				.findByUserName(userInstance.getUserName()));
		//assertNull("Found a User with userName YYY", userService.findByUserName("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<User> users = userService.searchByExample(userInstance);
			assertTrue(!users.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
