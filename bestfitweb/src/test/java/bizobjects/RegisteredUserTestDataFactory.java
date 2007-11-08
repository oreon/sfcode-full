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

			registeredUser.setFirstName("alpha");
			registeredUser.setLastName("Wilson");
			registeredUser.setDob(dateFormat.parse("2007.10.26 13:32:05 EDT"));
			registeredUser.setAge(3993);
			registeredUser.setGender("Mark");
			registeredUser.setImage("Eric");
			registeredUser.getUserAccount().setUsername("Mark36340");
			registeredUser.getUserAccount().setPassword("theta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("epsilon");
			registeredUser.getPrimaryAddress().setCity("pi");
			registeredUser.getPrimaryAddress().setZip("Lavendar");
			registeredUser.getPrimaryAddress().setEmail("theta77122");
			registeredUser.getPrimaryAddress().setCountry("Lavendar");
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

			registeredUser.setFirstName("pi");
			registeredUser.setLastName("Wilson");
			registeredUser.setDob(dateFormat.parse("2007.10.15 18:43:10 EDT"));
			registeredUser.setAge(5401);
			registeredUser.setGender("Eric");
			registeredUser.setImage("theta");
			registeredUser.getUserAccount().setUsername("John78053");
			registeredUser.getUserAccount().setPassword("theta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("beta");
			registeredUser.getPrimaryAddress().setCity("Eric");
			registeredUser.getPrimaryAddress().setZip("epsilon");
			registeredUser.getPrimaryAddress().setEmail("alpha80844");
			registeredUser.getPrimaryAddress().setCountry("John");
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

			registeredUser.setFirstName("John");
			registeredUser.setLastName("Malissa");
			registeredUser.setDob(dateFormat.parse("2007.12.03 03:03:43 EST"));
			registeredUser.setAge(4318);
			registeredUser.setGender("Eric");
			registeredUser.setImage("zeta");
			registeredUser.getUserAccount().setUsername("Lavendar71006");
			registeredUser.getUserAccount().setPassword("pi");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("zeta");
			registeredUser.getPrimaryAddress().setCity("Eric");
			registeredUser.getPrimaryAddress().setZip("Mark");
			registeredUser.getPrimaryAddress().setEmail("pi47467");
			registeredUser.getPrimaryAddress().setCountry("Wilson");
			registeredUser.getPrimaryAddress().setState("John");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFour() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("John");
			registeredUser.setLastName("Lavendar");
			registeredUser.setDob(dateFormat.parse("2007.11.08 12:55:25 EST"));
			registeredUser.setAge(1583);
			registeredUser.setGender("beta");
			registeredUser.setImage("Mark");
			registeredUser.getUserAccount().setUsername("John414");
			registeredUser.getUserAccount().setPassword("theta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("gamma");
			registeredUser.getPrimaryAddress().setCity("pi");
			registeredUser.getPrimaryAddress().setZip("epsilon");
			registeredUser.getPrimaryAddress().setEmail("epsilon88760");
			registeredUser.getPrimaryAddress().setCountry("beta");
			registeredUser.getPrimaryAddress().setState("epsilon");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFive() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Eric");
			registeredUser.setLastName("gamma");
			registeredUser.setDob(dateFormat.parse("2007.11.09 03:19:50 EST"));
			registeredUser.setAge(1837);
			registeredUser.setGender("pi");
			registeredUser.setImage("zeta");
			registeredUser.getUserAccount().setUsername("zeta61300");
			registeredUser.getUserAccount().setPassword("epsilon");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("theta");
			registeredUser.getPrimaryAddress().setCity("zeta");
			registeredUser.getPrimaryAddress().setZip("gamma");
			registeredUser.getPrimaryAddress().setEmail("Eric50620");
			registeredUser.getPrimaryAddress().setCountry("delta");
			registeredUser.getPrimaryAddress().setState("epsilon");

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
