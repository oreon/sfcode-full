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

			registeredUserInstance.setFirstName("Mark");
			registeredUserInstance.setLastName("zeta");
			registeredUserInstance.setDob(dateFormat
					.parse("2007.11.15 15:04:49 EST"));
			registeredUserInstance.setAge(2857);
			registeredUserInstance.setGender("Eric");
			registeredUserInstance.setImage("beta");
			registeredUserInstance.getUserAccount().setUsername("John99078");
			registeredUserInstance.getUserAccount().setPassword("zeta");
			registeredUserInstance.getUserAccount().setEnabled(true);
			registeredUserInstance.getPrimaryAddress()
					.setStreetAddress("theta");
			registeredUserInstance.getPrimaryAddress().setCity("theta");
			registeredUserInstance.getPrimaryAddress().setZip("theta");
			registeredUserInstance.getPrimaryAddress().setEmail("delta46486");
			registeredUserInstance.getPrimaryAddress().setCountry("Mark");
			registeredUserInstance.getPrimaryAddress().setState("alpha");

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

				registeredUser.setFirstName("theta");
				registeredUser.setLastName("Wilson");
				registeredUser.setDob(dateFormat
						.parse("2007.10.31 19:47:04 EDT"));
				registeredUser.setAge(258);
				registeredUser.setGender("John");
				registeredUser.setImage("delta");
				registeredUser.getUserAccount().setUsername("Wilson99863");
				registeredUser.getUserAccount().setPassword("Malissa");
				registeredUser.getUserAccount().setEnabled(true);
				registeredUser.getPrimaryAddress().setStreetAddress("Wilson");
				registeredUser.getPrimaryAddress().setCity("Malissa");
				registeredUser.getPrimaryAddress().setZip("Mark");
				registeredUser.getPrimaryAddress().setEmail("Malissa1232");
				registeredUser.getPrimaryAddress().setCountry("delta");
				registeredUser.getPrimaryAddress().setState("beta");

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

			registeredUser.setFirstName("John");
			registeredUser.setLastName("Eric");
			registeredUser.setDob(dateFormat.parse("2007.10.26 23:08:09 EDT"));
			registeredUser.setAge(205);
			registeredUser.setGender("John");
			registeredUser.setImage("gamma");
			registeredUser.getUserAccount().setUsername("alpha76580");
			registeredUser.getUserAccount().setPassword("Mark");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("theta");
			registeredUser.getPrimaryAddress().setCity("zeta");
			registeredUser.getPrimaryAddress().setZip("alpha");
			registeredUser.getPrimaryAddress().setEmail("Mark72494");
			registeredUser.getPrimaryAddress().setCountry("pi");
			registeredUser.getPrimaryAddress().setState("Lavendar");

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
