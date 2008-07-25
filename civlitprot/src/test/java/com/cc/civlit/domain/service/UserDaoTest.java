package com.cc.civlit.domain.service;

import com.cc.civlit.domain.auth.User;
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

			userInstance.setPassword("gamma");
			userInstance.setEnabled(true);
			userInstance.setUsername("zeta97107");

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

				user.setPassword("Lavendar");
				user.setEnabled(true);
				user.setUsername("gamma88701");

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

			user.setPassword("Wilson");
			user.setEnabled(true);
			user.setUsername("Mark66874");

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

		try {
			long count, newCount, diff = 0;
			count = userService.getCount();
			User user = (User) userTestDataFactory.loadOneRecord();
			userService.delete(user);
			newCount = userService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
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

	public void testFindByUsername() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a User with username ", userService
				.findByUsername(userInstance.getUsername()));
		//assertNull("Found a User with username YYY", userService.findByUsername("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<User> users = userService.searchByExample(userInstance);
			assertTrue(!users.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
