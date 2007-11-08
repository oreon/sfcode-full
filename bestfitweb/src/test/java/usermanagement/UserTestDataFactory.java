package usermanagement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import usermanagement.service.UserService;

@Transactional
public class UserTestDataFactory extends AbstractTestDataFactory<User> {

	List<User> users = new ArrayList<User>();

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

			user.setUsername("Lavendar39935");
			user.setPassword("Eric");
			user.setEnabled(true);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserTwo() {
		User user = new User();

		try {

			user.setUsername("alpha11420");
			user.setPassword("zeta");
			user.setEnabled(true);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserThree() {
		User user = new User();

		try {

			user.setUsername("John97644");
			user.setPassword("delta");
			user.setEnabled(true);

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserFour() {
		User user = new User();

		try {

			user.setUsername("epsilon85435");
			user.setPassword("zeta");
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

			user.setUsername("zeta31541");
			user.setPassword("theta");
			user.setEnabled(true);

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
			userService.save(user);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
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
