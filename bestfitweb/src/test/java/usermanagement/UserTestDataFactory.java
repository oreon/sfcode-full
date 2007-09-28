package usermanagement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import usermanagement.service.UserService;

public class UserTestDataFactory extends AbstractTestDataFactory {

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

			user.setUsername("theta88074");
			user.setPassword("pi");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserTwo() {
		User user = new User();

		try {

			user.setUsername("delta39367");
			user.setPassword("epsilon");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserThree() {
		User user = new User();

		try {

			user.setUsername("pi83468");
			user.setPassword("pi");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserFour() {
		User user = new User();

		try {

			user.setUsername("epsilon86642");
			user.setPassword("epsilon");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User createUserFive() {
		User user = new User();

		try {

			user.setUsername("pi81549");
			user.setPassword("alpha");

			register(user);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User loadUser() {
		List<User> users = userService.loadAll();

		if (users.isEmpty()) {
			persistAll();
			users = userService.loadAll();
		}

		return users.get(new Random().nextInt(users.size()));
	}

	List<User> getAllAsList() {

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
		if (!isPersistable())
			return;

		for (User user : users) {
			userService.save(user);
		}
	}

	/** Will return a random number of PlacedOrders
	 * @return
	 */
	List<User> getFew() {
		List<User> all = getAllAsList();
		int numToChoose = new Random(1212343).nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<User>();
		List returnList = new ArrayList<User>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random(1212343).nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(numToChoose);
		}

		return returnList;
	}

}
