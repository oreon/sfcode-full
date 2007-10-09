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

			registeredUser.setFirstName("theta");
			registeredUser.setLastName("delta");
			registeredUser.setDob(dateFormat.parse("2007.10.28 04:25:11 EDT"));
			registeredUser.setGender("pi");
			registeredUser.setImage("pi");
			registeredUser.getUserAccount().setUsername("beta97035");
			registeredUser.getUserAccount().setPassword("Eric");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("beta");
			registeredUser.getPrimaryAddress().setCity("delta");
			registeredUser.getPrimaryAddress().setZip("John");
			registeredUser.getPrimaryAddress().setEmail("beta78610");
			registeredUser.getPrimaryAddress().setCountry("Lavendar");
			registeredUser.getPrimaryAddress().setState("gamma");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserTwo() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Lavendar");
			registeredUser.setLastName("gamma");
			registeredUser.setDob(dateFormat.parse("2007.10.20 14:14:38 EDT"));
			registeredUser.setGender("Wilson");
			registeredUser.setImage("Eric");
			registeredUser.getUserAccount().setUsername("Wilson97953");
			registeredUser.getUserAccount().setPassword("delta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Mark");
			registeredUser.getPrimaryAddress().setCity("zeta");
			registeredUser.getPrimaryAddress().setZip("John");
			registeredUser.getPrimaryAddress().setEmail("Lavendar65857");
			registeredUser.getPrimaryAddress().setCountry("theta");
			registeredUser.getPrimaryAddress().setState("beta");

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
			registeredUser.setLastName("Malissa");
			registeredUser.setDob(dateFormat.parse("2007.10.23 14:17:25 EDT"));
			registeredUser.setGender("Wilson");
			registeredUser.setImage("alpha");
			registeredUser.getUserAccount().setUsername("Eric60243");
			registeredUser.getUserAccount().setPassword("alpha");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("zeta");
			registeredUser.getPrimaryAddress().setCity("Eric");
			registeredUser.getPrimaryAddress().setZip("delta");
			registeredUser.getPrimaryAddress().setEmail("Malissa45980");
			registeredUser.getPrimaryAddress().setCountry("delta");
			registeredUser.getPrimaryAddress().setState("beta");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFour() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("theta");
			registeredUser.setLastName("Mark");
			registeredUser.setDob(dateFormat.parse("2007.10.03 23:48:31 EDT"));
			registeredUser.setGender("epsilon");
			registeredUser.setImage("delta");
			registeredUser.getUserAccount().setUsername("theta72908");
			registeredUser.getUserAccount().setPassword("theta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("theta");
			registeredUser.getPrimaryAddress().setCity("Eric");
			registeredUser.getPrimaryAddress().setZip("epsilon");
			registeredUser.getPrimaryAddress().setEmail("delta65296");
			registeredUser.getPrimaryAddress().setCountry("theta");
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
			registeredUser.setLastName("theta");
			registeredUser.setDob(dateFormat.parse("2007.09.24 12:55:43 EDT"));
			registeredUser.setGender("Wilson");
			registeredUser.setImage("Lavendar");
			registeredUser.getUserAccount().setUsername("alpha35831");
			registeredUser.getUserAccount().setPassword("John");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("theta");
			registeredUser.getPrimaryAddress().setCity("Mark");
			registeredUser.getPrimaryAddress().setZip("Malissa");
			registeredUser.getPrimaryAddress().setEmail("zeta96307");
			registeredUser.getPrimaryAddress().setCountry("delta");
			registeredUser.getPrimaryAddress().setState("pi");

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
