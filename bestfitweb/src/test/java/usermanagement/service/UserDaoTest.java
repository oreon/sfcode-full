package usermanagement.service;

import usermanagement.User;
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

			userInstance.setUsername("theta68085");
			userInstance.setPassword("beta");
			userInstance.setEnabled(true);

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

				user.setUsername("gamma68170");
				user.setPassword("Malissa");
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

			user.setUsername("Mark83112");
			user.setPassword("pi");
			user.setEnabled(true);

			userService.save(user);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(userService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
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

}
