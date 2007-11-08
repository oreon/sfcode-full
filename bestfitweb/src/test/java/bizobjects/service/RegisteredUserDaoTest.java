package bizobjects.service;

import bizobjects.RegisteredUser;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class RegisteredUserDaoTest extends AbstractJpaTests {

	protected RegisteredUser registeredUserInstance = new RegisteredUser();

	protected RegisteredUserService registeredUserService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setRegisteredUserService(
			RegisteredUserService registeredUserService) {
		this.registeredUserService = registeredUserService;
	}

	protected TestDataFactory registeredUserTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("registeredUserTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			registeredUserInstance.setFirstName("alpha");
			registeredUserInstance.setLastName("pi");
			registeredUserInstance.setDob(dateFormat
					.parse("2007.11.21 04:15:25 EST"));
			registeredUserInstance.setAge(5743);
			registeredUserInstance.setGender("zeta");
			registeredUserInstance.setImage("Mark");
			registeredUserInstance.getUserAccount().setUsername("alpha30610");
			registeredUserInstance.getUserAccount().setPassword("Wilson");
			registeredUserInstance.getUserAccount().setEnabled(true);
			registeredUserInstance.getPrimaryAddress().setStreetAddress(
					"Lavendar");
			registeredUserInstance.getPrimaryAddress().setCity("zeta");
			registeredUserInstance.getPrimaryAddress().setZip("alpha");
			registeredUserInstance.getPrimaryAddress().setEmail("Mark47402");
			registeredUserInstance.getPrimaryAddress().setCountry("Mark");
			registeredUserInstance.getPrimaryAddress().setState("Malissa");

			registeredUserService.save(registeredUserInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			RegisteredUser registeredUser = new RegisteredUser();

			try {

				registeredUser.setFirstName("John");
				registeredUser.setLastName("alpha");
				registeredUser.setDob(dateFormat
						.parse("2007.10.28 13:18:45 EDT"));
				registeredUser.setAge(2369);
				registeredUser.setGender("John");
				registeredUser.setImage("pi");
				registeredUser.getUserAccount().setUsername("John83343");
				registeredUser.getUserAccount().setPassword("Wilson");
				registeredUser.getUserAccount().setEnabled(true);
				registeredUser.getPrimaryAddress().setStreetAddress("Mark");
				registeredUser.getPrimaryAddress().setCity("alpha");
				registeredUser.getPrimaryAddress().setZip("beta");
				registeredUser.getPrimaryAddress().setEmail("beta40971");
				registeredUser.getPrimaryAddress().setCountry("alpha");
				registeredUser.getPrimaryAddress().setState("pi");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			registeredUserService.save(registeredUser);
			assertNotNull(registeredUser.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			RegisteredUser registeredUser = (RegisteredUser) registeredUserTestDataFactory
					.loadOneRecord();

			registeredUser.setFirstName("Lavendar");
			registeredUser.setLastName("alpha");
			registeredUser.setDob(dateFormat.parse("2007.11.21 04:27:03 EST"));
			registeredUser.setAge(6322);
			registeredUser.setGender("theta");
			registeredUser.setImage("Wilson");
			registeredUser.getUserAccount().setUsername("Lavendar54281");
			registeredUser.getUserAccount().setPassword("Wilson");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("pi");
			registeredUser.getPrimaryAddress().setCity("zeta");
			registeredUser.getPrimaryAddress().setZip("Wilson");
			registeredUser.getPrimaryAddress().setEmail("alpha94102");
			registeredUser.getPrimaryAddress().setCountry("zeta");
			registeredUser.getPrimaryAddress().setState("delta");

			registeredUserService.save(registeredUser);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(registeredUserService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			RegisteredUser registeredUser = registeredUserService
					.load(registeredUserInstance.getId());
			assertNotNull(registeredUser.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByLastName() {
		if (!bTest)
			return;

		List<RegisteredUser> registeredUsers = registeredUserService
				.findByLastName(registeredUserInstance.getLastName());
		assertTrue(!registeredUsers.isEmpty());

		//negative test
		//registeredUsers = 
		registeredUserService.findByLastName(registeredUserInstance
				.getLastName());
		//assertTrue(registeredUsers.isEmpty()); 

	}

	public void testFindByUsername() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a RegisteredUser with username ",
				registeredUserService.findByUsername(registeredUserInstance
						.getUserAccount().getUsername()));
		//assertNull("Found a RegisteredUser with username YYY", registeredUserService.findByUsername("YYY"));			

	}

	public void testFindByEmail() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a RegisteredUser with email ",
				registeredUserService.findByEmail(registeredUserInstance
						.getPrimaryAddress().getEmail()));
		//assertNull("Found a RegisteredUser with email YYY", registeredUserService.findByEmail("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<RegisteredUser> registeredUsers = registeredUserService
					.searchByExample(registeredUserInstance);
			assertTrue(!registeredUsers.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
