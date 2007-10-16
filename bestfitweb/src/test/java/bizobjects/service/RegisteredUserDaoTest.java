package bizobjects.service;

import bizobjects.RegisteredUser;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;

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

			registeredUserInstance.setFirstName("gamma");
			registeredUserInstance.setLastName("Lavendar");
			registeredUserInstance.setDob(dateFormat
					.parse("2007.09.25 19:14:33 EDT"));
			registeredUserInstance.setGender("theta");
			registeredUserInstance.setImage("Wilson");
			registeredUserInstance.getUserAccount().setUsername("alpha87080");
			registeredUserInstance.getUserAccount().setPassword("gamma");
			registeredUserInstance.getUserAccount().setEnabled(true);
			registeredUserInstance.getPrimaryAddress()
					.setStreetAddress("alpha");
			registeredUserInstance.getPrimaryAddress().setCity("beta");
			registeredUserInstance.getPrimaryAddress().setZip("Wilson");
			registeredUserInstance.getPrimaryAddress().setEmail("alpha7756");
			registeredUserInstance.getPrimaryAddress().setCountry("delta");
			registeredUserInstance.getPrimaryAddress().setState("zeta");

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

				registeredUser.setFirstName("gamma");
				registeredUser.setLastName("Lavendar");
				registeredUser.setDob(dateFormat
						.parse("2007.10.22 01:07:53 EDT"));
				registeredUser.setGender("zeta");
				registeredUser.setImage("John");
				registeredUser.getUserAccount().setUsername("pi52470");
				registeredUser.getUserAccount().setPassword("delta");
				registeredUser.getUserAccount().setEnabled(true);
				registeredUser.getPrimaryAddress().setStreetAddress("gamma");
				registeredUser.getPrimaryAddress().setCity("epsilon");
				registeredUser.getPrimaryAddress().setZip("delta");
				registeredUser.getPrimaryAddress().setEmail("Wilson56419");
				registeredUser.getPrimaryAddress().setCountry("Malissa");
				registeredUser.getPrimaryAddress().setState("Mark");

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

			registeredUser.setFirstName("Mark");
			registeredUser.setLastName("Mark");
			registeredUser.setDob(dateFormat.parse("2007.09.24 03:18:25 EDT"));
			registeredUser.setGender("pi");
			registeredUser.setImage("gamma");
			registeredUser.getUserAccount().setUsername("theta75987");
			registeredUser.getUserAccount().setPassword("zeta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("zeta");
			registeredUser.getPrimaryAddress().setCity("delta");
			registeredUser.getPrimaryAddress().setZip("John");
			registeredUser.getPrimaryAddress().setEmail("delta47624");
			registeredUser.getPrimaryAddress().setCountry("gamma");
			registeredUser.getPrimaryAddress().setState("Lavendar");

			registeredUserService.save(registeredUser);

		} catch (Exception e) {
			fail(e.getMessage());
		}
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
