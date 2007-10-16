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

			registeredUserInstance.setFirstName("delta");
			registeredUserInstance.setLastName("Malissa");
			registeredUserInstance.setDob(dateFormat
					.parse("2007.10.17 11:37:05 EDT"));
			registeredUserInstance.setGender("John");
			registeredUserInstance.setImage("epsilon");
			registeredUserInstance.getUserAccount().setUsername("theta29716");
			registeredUserInstance.getUserAccount().setPassword("Wilson");
			registeredUserInstance.getUserAccount().setEnabled(true);
			registeredUserInstance.getPrimaryAddress().setStreetAddress("Eric");
			registeredUserInstance.getPrimaryAddress().setCity("epsilon");
			registeredUserInstance.getPrimaryAddress().setZip("Wilson");
			registeredUserInstance.getPrimaryAddress().setEmail("beta12537");
			registeredUserInstance.getPrimaryAddress().setCountry("gamma");
			registeredUserInstance.getPrimaryAddress().setState("delta");

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

				registeredUser.setFirstName("zeta");
				registeredUser.setLastName("beta");
				registeredUser.setDob(dateFormat
						.parse("2007.10.22 13:10:25 EDT"));
				registeredUser.setGender("gamma");
				registeredUser.setImage("Eric");
				registeredUser.getUserAccount().setUsername("zeta90170");
				registeredUser.getUserAccount().setPassword("Mark");
				registeredUser.getUserAccount().setEnabled(true);
				registeredUser.getPrimaryAddress().setStreetAddress("theta");
				registeredUser.getPrimaryAddress().setCity("Mark");
				registeredUser.getPrimaryAddress().setZip("Mark");
				registeredUser.getPrimaryAddress().setEmail("John67081");
				registeredUser.getPrimaryAddress().setCountry("pi");
				registeredUser.getPrimaryAddress().setState("alpha");

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

			registeredUser.setFirstName("gamma");
			registeredUser.setLastName("pi");
			registeredUser.setDob(dateFormat.parse("2007.11.02 15:23:12 EDT"));
			registeredUser.setGender("John");
			registeredUser.setImage("Mark");
			registeredUser.getUserAccount().setUsername("John38076");
			registeredUser.getUserAccount().setPassword("epsilon");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("zeta");
			registeredUser.getPrimaryAddress().setCity("zeta");
			registeredUser.getPrimaryAddress().setZip("delta");
			registeredUser.getPrimaryAddress().setEmail("zeta92765");
			registeredUser.getPrimaryAddress().setCountry("Eric");
			registeredUser.getPrimaryAddress().setState("John");

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
