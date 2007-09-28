package usermanagement.service;

import usermanagement.User;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

public class UserDaoTest extends AbstractJpaTests {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml"};
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {

	}

	public void testSave() {
		//test saving a new record and updating an existing record;
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {
		//return null;
	}

	public void testFindByUsername() {

		assertNotNull("Couldn't find a User with username XXX", userService
				.findByUsername("XXX"));
		assertNull("Found a User with username YYY", userService
				.findByUsername("YYY"));

	}

	public void testSearchByExample() {
		User user = new User();
		//user.setFirstName("Eri");
		List<User> users = userService.searchByExample(user);
		assertTrue(!users.isEmpty());
	}

}
