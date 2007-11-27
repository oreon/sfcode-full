package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

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

			registeredUser.setFirstName("pi");
			registeredUser.setLastName("theta");
			registeredUser.setDob(dateFormat.parse("2007.11.01 02:31:06 EDT"));
			registeredUser.setAge(774);
			registeredUser.setGender("alpha");
			registeredUser.setImage("delta");
			registeredUser.getUserAccount().setUsername("Eric80001");
			registeredUser.getUserAccount().setPassword("pi");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Mark");
			registeredUser.getPrimaryAddress().setCity("Wilson");
			registeredUser.getPrimaryAddress().setZip("Malissa");
			registeredUser.getPrimaryAddress().setEmail("delta77815");
			registeredUser.getPrimaryAddress().setCountry("beta");
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

			registeredUser.setFirstName("Eric");
			registeredUser.setLastName("alpha");
			registeredUser.setDob(dateFormat.parse("2007.11.07 13:41:06 EST"));
			registeredUser.setAge(3420);
			registeredUser.setGender("beta");
			registeredUser.setImage("zeta");
			registeredUser.getUserAccount().setUsername("Lavendar4374");
			registeredUser.getUserAccount().setPassword("Wilson");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("zeta");
			registeredUser.getPrimaryAddress().setCity("Wilson");
			registeredUser.getPrimaryAddress().setZip("Lavendar");
			registeredUser.getPrimaryAddress().setEmail("pi76077");
			registeredUser.getPrimaryAddress().setCountry("Malissa");
			registeredUser.getPrimaryAddress().setState("Lavendar");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserThree() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("beta");
			registeredUser.setLastName("gamma");
			registeredUser.setDob(dateFormat.parse("2007.10.23 16:26:05 EDT"));
			registeredUser.setAge(1830);
			registeredUser.setGender("Lavendar");
			registeredUser.setImage("delta");
			registeredUser.getUserAccount().setUsername("pi22336");
			registeredUser.getUserAccount().setPassword("theta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("epsilon");
			registeredUser.getPrimaryAddress().setCity("Lavendar");
			registeredUser.getPrimaryAddress().setZip("epsilon");
			registeredUser.getPrimaryAddress().setEmail("beta13559");
			registeredUser.getPrimaryAddress().setCountry("Eric");
			registeredUser.getPrimaryAddress().setState("epsilon");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFour() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Malissa");
			registeredUser.setLastName("epsilon");
			registeredUser.setDob(dateFormat.parse("2007.12.02 01:55:32 EST"));
			registeredUser.setAge(938);
			registeredUser.setGender("gamma");
			registeredUser.setImage("Malissa");
			registeredUser.getUserAccount().setUsername("Lavendar33939");
			registeredUser.getUserAccount().setPassword("epsilon");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("alpha");
			registeredUser.getPrimaryAddress().setCity("gamma");
			registeredUser.getPrimaryAddress().setZip("theta");
			registeredUser.getPrimaryAddress().setEmail("gamma61770");
			registeredUser.getPrimaryAddress().setCountry("delta");
			registeredUser.getPrimaryAddress().setState("gamma");

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
			registeredUser.setDob(dateFormat.parse("2007.10.17 10:57:46 EDT"));
			registeredUser.setAge(8562);
			registeredUser.setGender("Malissa");
			registeredUser.setImage("gamma");
			registeredUser.getUserAccount().setUsername("gamma88716");
			registeredUser.getUserAccount().setPassword("John");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("pi");
			registeredUser.getPrimaryAddress().setCity("Malissa");
			registeredUser.getPrimaryAddress().setZip("delta");
			registeredUser.getPrimaryAddress().setEmail("zeta35458");
			registeredUser.getPrimaryAddress().setCountry("beta");
			registeredUser.getPrimaryAddress().setState("Wilson");

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
			RegisteredUser registeredUser = createRandomRegisteredUser();
			registeredUserService.save(registeredUser);
		}
	}

	public RegisteredUser createRandomRegisteredUser() {
		RegisteredUser registeredUser = new RegisteredUser();

		registeredUser.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		registeredUser.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		registeredUser.setDob((java.util.Date) RandomValueGeneratorFactory
				.createInstance("java.util.Date"));
		registeredUser.setAge((Integer) RandomValueGeneratorFactory
				.createInstance("int"));
		registeredUser.setGender((String) RandomValueGeneratorFactory
				.createInstance("String"));
		registeredUser.setImage((String) RandomValueGeneratorFactory
				.createInstance("String"));
		registeredUser.getUserAccount().setUsername(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		registeredUser.getUserAccount().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		registeredUser.getUserAccount()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("boolean"));
		registeredUser.getPrimaryAddress().setStreetAddress(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		registeredUser.getPrimaryAddress().setCity(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		registeredUser.getPrimaryAddress().setZip(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		registeredUser.getPrimaryAddress().setEmail(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		registeredUser.getPrimaryAddress().setCountry(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		registeredUser.getPrimaryAddress().setState(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		return registeredUser;
	}

}
