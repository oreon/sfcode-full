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

			registeredUser.setFirstName("Mark");
			registeredUser.setLastName("Lavendar");
			registeredUser.setDob(dateFormat.parse("2007.11.05 18:37:37 EST"));
			registeredUser.setAge(1625);
			registeredUser.setGender("Eric");
			registeredUser.setImage("John");
			registeredUser.getUserAccount().setUsername("alpha32776");
			registeredUser.getUserAccount().setPassword("epsilon");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Eric");
			registeredUser.getPrimaryAddress().setCity("Wilson");
			registeredUser.getPrimaryAddress().setZip("John");
			registeredUser.getPrimaryAddress().setEmail("Malissa84551");
			registeredUser.getPrimaryAddress().setCountry("beta");
			registeredUser.getPrimaryAddress().setState("delta");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserTwo() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("zeta");
			registeredUser.setLastName("pi");
			registeredUser.setDob(dateFormat.parse("2007.10.23 05:18:09 EDT"));
			registeredUser.setAge(8516);
			registeredUser.setGender("beta");
			registeredUser.setImage("Eric");
			registeredUser.getUserAccount().setUsername("zeta72666");
			registeredUser.getUserAccount().setPassword("Lavendar");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Malissa");
			registeredUser.getPrimaryAddress().setCity("alpha");
			registeredUser.getPrimaryAddress().setZip("Malissa");
			registeredUser.getPrimaryAddress().setEmail("delta94964");
			registeredUser.getPrimaryAddress().setCountry("Malissa");
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

			registeredUser.setFirstName("delta");
			registeredUser.setLastName("delta");
			registeredUser.setDob(dateFormat.parse("2007.11.28 14:53:11 EST"));
			registeredUser.setAge(6047);
			registeredUser.setGender("zeta");
			registeredUser.setImage("Mark");
			registeredUser.getUserAccount().setUsername("Wilson66497");
			registeredUser.getUserAccount().setPassword("Lavendar");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("zeta");
			registeredUser.getPrimaryAddress().setCity("delta");
			registeredUser.getPrimaryAddress().setZip("gamma");
			registeredUser.getPrimaryAddress().setEmail("John78468");
			registeredUser.getPrimaryAddress().setCountry("zeta");
			registeredUser.getPrimaryAddress().setState("Lavendar");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFour() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Eric");
			registeredUser.setLastName("delta");
			registeredUser.setDob(dateFormat.parse("2007.11.17 02:32:02 EST"));
			registeredUser.setAge(7104);
			registeredUser.setGender("Wilson");
			registeredUser.setImage("Malissa");
			registeredUser.getUserAccount().setUsername("zeta4981");
			registeredUser.getUserAccount().setPassword("pi");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("beta");
			registeredUser.getPrimaryAddress().setCity("Mark");
			registeredUser.getPrimaryAddress().setZip("epsilon");
			registeredUser.getPrimaryAddress().setEmail("zeta7417");
			registeredUser.getPrimaryAddress().setCountry("delta");
			registeredUser.getPrimaryAddress().setState("pi");

			register(registeredUser);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return registeredUser;
	}

	public RegisteredUser createRegisteredUserFive() {
		RegisteredUser registeredUser = new RegisteredUser();

		try {

			registeredUser.setFirstName("Lavendar");
			registeredUser.setLastName("Lavendar");
			registeredUser.setDob(dateFormat.parse("2007.11.06 13:20:57 EST"));
			registeredUser.setAge(1109);
			registeredUser.setGender("John");
			registeredUser.setImage("Malissa");
			registeredUser.getUserAccount().setUsername("Wilson64132");
			registeredUser.getUserAccount().setPassword("zeta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("alpha");
			registeredUser.getPrimaryAddress().setCity("delta");
			registeredUser.getPrimaryAddress().setZip("Eric");
			registeredUser.getPrimaryAddress().setEmail("alpha33937");
			registeredUser.getPrimaryAddress().setCountry("theta");
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
