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

			registeredUser.setFirstName("Eric");
			registeredUser.setLastName("alpha");
			registeredUser.setDob(dateFormat.parse("2007.11.09 17:26:15 EST"));
			registeredUser.setGender("alpha");
			registeredUser.setImage("delta");
			registeredUser.getUserAccount().setUsername("alpha60564");
			registeredUser.getUserAccount().setPassword("epsilon");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Eric");
			registeredUser.getPrimaryAddress().setCity("epsilon");
			registeredUser.getPrimaryAddress().setZip("zeta");
			registeredUser.getPrimaryAddress().setEmail("pi24642");
			registeredUser.getPrimaryAddress().setCountry("pi");
			registeredUser.getPrimaryAddress().setState("John");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserTwo() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("theta");
			registeredUser.setLastName("gamma");
			registeredUser.setDob(dateFormat.parse("2007.09.25 12:04:00 EDT"));
			registeredUser.setGender("gamma");
			registeredUser.setImage("Malissa");
			registeredUser.getUserAccount().setUsername("Malissa713");
			registeredUser.getUserAccount().setPassword("beta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Mark");
			registeredUser.getPrimaryAddress().setCity("Wilson");
			registeredUser.getPrimaryAddress().setZip("zeta");
			registeredUser.getPrimaryAddress().setEmail("Mark28943");
			registeredUser.getPrimaryAddress().setCountry("Eric");
			registeredUser.getPrimaryAddress().setState("Malissa");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserThree() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Mark");
			registeredUser.setLastName("Eric");
			registeredUser.setDob(dateFormat.parse("2007.10.09 11:31:13 EDT"));
			registeredUser.setGender("Wilson");
			registeredUser.setImage("alpha");
			registeredUser.getUserAccount().setUsername("John78651");
			registeredUser.getUserAccount().setPassword("Lavendar");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Mark");
			registeredUser.getPrimaryAddress().setCity("John");
			registeredUser.getPrimaryAddress().setZip("delta");
			registeredUser.getPrimaryAddress().setEmail("alpha84103");
			registeredUser.getPrimaryAddress().setCountry("alpha");
			registeredUser.getPrimaryAddress().setState("Mark");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFour() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("epsilon");
			registeredUser.setLastName("epsilon");
			registeredUser.setDob(dateFormat.parse("2007.11.06 19:51:13 EST"));
			registeredUser.setGender("Mark");
			registeredUser.setImage("gamma");
			registeredUser.getUserAccount().setUsername("gamma82726");
			registeredUser.getUserAccount().setPassword("Lavendar");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Wilson");
			registeredUser.getPrimaryAddress().setCity("epsilon");
			registeredUser.getPrimaryAddress().setZip("zeta");
			registeredUser.getPrimaryAddress().setEmail("Eric13454");
			registeredUser.getPrimaryAddress().setCountry("gamma");
			registeredUser.getPrimaryAddress().setState("Lavendar");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFive() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("epsilon");
			registeredUser.setLastName("alpha");
			registeredUser.setDob(dateFormat.parse("2007.10.16 06:08:26 EDT"));
			registeredUser.setGender("Lavendar");
			registeredUser.setImage("epsilon");
			registeredUser.getUserAccount().setUsername("delta35390");
			registeredUser.getUserAccount().setPassword("delta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("John");
			registeredUser.getPrimaryAddress().setCity("alpha");
			registeredUser.getPrimaryAddress().setZip("delta");
			registeredUser.getPrimaryAddress().setEmail("Lavendar28832");
			registeredUser.getPrimaryAddress().setCountry("Malissa");
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
