package usermanagement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import org.witchcraft.model.support.TestDataFactory;

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

			user.setUsername("zeta32522");
			user.setPassword("John");
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

			user.setUsername("gamma2879");
			user.setPassword("alpha");
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

			user.setUsername("pi76636");
			user.setPassword("pi");
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

			user.setUsername("zeta72729");
			user.setPassword("Wilson");
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

			user.setUsername("Lavendar24978");
			user.setPassword("gamma");
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

}
