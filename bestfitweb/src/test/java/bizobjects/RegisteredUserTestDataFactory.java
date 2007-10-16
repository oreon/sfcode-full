package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.springframework.transaction.annotation.Transactional;

import bizobjects.service.RegisteredUserService;

@Transactional
public class RegisteredUserTestDataFactory
		extends
			AbstractTestDataFactory<RegisteredUser> {

	List<RegisteredUser> registeredUsers = new ArrayList<RegisteredUser>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	RegisteredUserService registeredUserService;

	public RegisteredUserService getRegisteredUserService() {
		return registeredUserService;
	}

	public void setRegisteredUserService(
			RegisteredUserService registeredUserService) {
		this.registeredUserService = registeredUserService;
	}

	public void register(RegisteredUser registeredUser) {
		registeredUsers.add(registeredUser);
	}

	public RegisteredUser createRegisteredUserOne() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("gamma");
			registeredUser.setLastName("alpha");
			registeredUser.setDob(dateFormat.parse("2007.10.23 23:57:05 EDT"));
			registeredUser.setGender("alpha");
			registeredUser.setImage("zeta");
			registeredUser.getUserAccount().setUsername("Eric82371");
			registeredUser.getUserAccount().setPassword("beta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("zeta");
			registeredUser.getPrimaryAddress().setCity("theta");
			registeredUser.getPrimaryAddress().setZip("gamma");
			registeredUser.getPrimaryAddress().setEmail("John16654");
			registeredUser.getPrimaryAddress().setCountry("zeta");
			registeredUser.getPrimaryAddress().setState("Wilson");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserTwo() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Eric");
			registeredUser.setLastName("theta");
			registeredUser.setDob(dateFormat.parse("2007.11.04 04:12:07 EST"));
			registeredUser.setGender("beta");
			registeredUser.setImage("zeta");
			registeredUser.getUserAccount().setUsername("Lavendar97800");
			registeredUser.getUserAccount().setPassword("Mark");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Lavendar");
			registeredUser.getPrimaryAddress().setCity("gamma");
			registeredUser.getPrimaryAddress().setZip("gamma");
			registeredUser.getPrimaryAddress().setEmail("gamma62945");
			registeredUser.getPrimaryAddress().setCountry("zeta");
			registeredUser.getPrimaryAddress().setState("zeta");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserThree() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Wilson");
			registeredUser.setLastName("Mark");
			registeredUser.setDob(dateFormat.parse("2007.09.21 22:10:58 EDT"));
			registeredUser.setGender("Mark");
			registeredUser.setImage("theta");
			registeredUser.getUserAccount().setUsername("delta5283");
			registeredUser.getUserAccount().setPassword("John");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Wilson");
			registeredUser.getPrimaryAddress().setCity("epsilon");
			registeredUser.getPrimaryAddress().setZip("zeta");
			registeredUser.getPrimaryAddress().setEmail("zeta99706");
			registeredUser.getPrimaryAddress().setCountry("theta");
			registeredUser.getPrimaryAddress().setState("zeta");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFour() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("delta");
			registeredUser.setLastName("Wilson");
			registeredUser.setDob(dateFormat.parse("2007.11.05 22:00:58 EST"));
			registeredUser.setGender("beta");
			registeredUser.setImage("zeta");
			registeredUser.getUserAccount().setUsername("gamma41813");
			registeredUser.getUserAccount().setPassword("Wilson");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Malissa");
			registeredUser.getPrimaryAddress().setCity("Malissa");
			registeredUser.getPrimaryAddress().setZip("Malissa");
			registeredUser.getPrimaryAddress().setEmail("gamma87448");
			registeredUser.getPrimaryAddress().setCountry("epsilon");
			registeredUser.getPrimaryAddress().setState("theta");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFive() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("John");
			registeredUser.setLastName("Malissa");
			registeredUser.setDob(dateFormat.parse("2007.11.03 18:16:32 EDT"));
			registeredUser.setGender("zeta");
			registeredUser.setImage("Mark");
			registeredUser.getUserAccount().setUsername("Wilson76930");
			registeredUser.getUserAccount().setPassword("Malissa");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Mark");
			registeredUser.getPrimaryAddress().setCity("Eric");
			registeredUser.getPrimaryAddress().setZip("alpha");
			registeredUser.getPrimaryAddress().setEmail("theta37616");
			registeredUser.getPrimaryAddress().setCountry("epsilon");
			registeredUser.getPrimaryAddress().setState("John");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser loadOneRecord() {
		List<RegisteredUser> registeredUsers = registeredUserService.loadAll();

		if (registeredUsers.isEmpty()) {
			persistAll();
			registeredUsers = registeredUserService.loadAll();
		}

		return registeredUsers
				.get(new Random().nextInt(registeredUsers.size()));
	}

	public List<RegisteredUser> getAllAsList() {

		if (registeredUsers.isEmpty()) {
			createRegisteredUserOne();
			createRegisteredUserTwo();
			createRegisteredUserThree();
			createRegisteredUserFour();
			createRegisteredUserFive();

		}

		return registeredUsers;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (RegisteredUser registeredUser : registeredUsers) {
			registeredUserService.save(registeredUser);
		}

		alreadyPersisted = true;
	}

}
