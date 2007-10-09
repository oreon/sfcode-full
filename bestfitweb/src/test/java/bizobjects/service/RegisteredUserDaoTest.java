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

			registeredUserInstance.setFirstName("John");
			registeredUserInstance.setLastName("pi");
			registeredUserInstance.setDob(dateFormat
					.parse("2007.09.15 09:30:45 EDT"));
			registeredUserInstance.setGender("alpha");
			registeredUserInstance.setImage("Eric");
			registeredUserInstance.getUserAccount().setUsername("delta45277");
			registeredUserInstance.getUserAccount().setPassword("John");
			registeredUserInstance.getUserAccount().setEnabled(true);
			registeredUserInstance.getPrimaryAddress().setStreetAddress("pi");
			registeredUserInstance.getPrimaryAddress().setCity("Lavendar");
			registeredUserInstance.getPrimaryAddress().setZip("Malissa");
			registeredUserInstance.getPrimaryAddress().setEmail("Wilson67395");
			registeredUserInstance.getPrimaryAddress().setCountry("alpha");
			registeredUserInstance.getPrimaryAddress().setState("Wilson");

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

				registeredUser.setFirstName("Mark");
				registeredUser.setLastName("gamma");
				registeredUser.setDob(dateFormat
						.parse("2007.10.10 23:41:18 EDT"));
				registeredUser.setGender("gamma");
				registeredUser.setImage("Wilson");
				registeredUser.getUserAccount().setUsername("alpha40124");
				registeredUser.getUserAccount().setPassword("John");
				registeredUser.getUserAccount().setEnabled(true);
				registeredUser.getPrimaryAddress().setStreetAddress("alpha");
				registeredUser.getPrimaryAddress().setCity("Mark");
				registeredUser.getPrimaryAddress().setZip("Wilson");
				registeredUser.getPrimaryAddress().setEmail("alpha57801");
				registeredUser.getPrimaryAddress().setCountry("Lavendar");
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

			registeredUser.setFirstName("zeta");
			registeredUser.setLastName("Mark");
			registeredUser.setDob(dateFormat.parse("2007.10.31 15:39:03 EDT"));
			registeredUser.setGender("pi");
			registeredUser.setImage("gamma");
			registeredUser.getUserAccount().setUsername("beta41817");
			registeredUser.getUserAccount().setPassword("delta");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("beta");
			registeredUser.getPrimaryAddress().setCity("pi");
			registeredUser.getPrimaryAddress().setZip("Lavendar");
			registeredUser.getPrimaryAddress().setEmail("epsilon91780");
			registeredUser.getPrimaryAddress().setCountry("alpha");
			registeredUser.getPrimaryAddress().setState("Malissa");

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
