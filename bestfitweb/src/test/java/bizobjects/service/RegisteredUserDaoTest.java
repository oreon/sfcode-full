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

			registeredUserInstance.setFirstName("Malissa");
			registeredUserInstance.setLastName("delta");
			registeredUserInstance.setDob(dateFormat
					.parse("2007.11.29 18:50:01 EST"));
			registeredUserInstance.setAge(7730);
			registeredUserInstance.setGender("delta");
			registeredUserInstance.setImage("alpha");
			registeredUserInstance.getUserAccount().setUsername("beta91810");
			registeredUserInstance.getUserAccount().setPassword("epsilon");
			registeredUserInstance.getUserAccount().setEnabled(true);
			registeredUserInstance.getPrimaryAddress().setStreetAddress("Mark");
			registeredUserInstance.getPrimaryAddress().setCity("Mark");
			registeredUserInstance.getPrimaryAddress().setZip("Lavendar");
			registeredUserInstance.getPrimaryAddress().setEmail("Eric11753");
			registeredUserInstance.getPrimaryAddress().setCountry("gamma");
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

				registeredUser.setFirstName("delta");
				registeredUser.setLastName("alpha");
				registeredUser.setDob(dateFormat
						.parse("2007.11.11 00:54:26 EST"));
				registeredUser.setAge(7996);
				registeredUser.setGender("alpha");
				registeredUser.setImage("Eric");
				registeredUser.getUserAccount().setUsername("alpha74476");
				registeredUser.getUserAccount().setPassword("Wilson");
				registeredUser.getUserAccount().setEnabled(true);
				registeredUser.getPrimaryAddress().setStreetAddress("Wilson");
				registeredUser.getPrimaryAddress().setCity("Malissa");
				registeredUser.getPrimaryAddress().setZip("Wilson");
				registeredUser.getPrimaryAddress().setEmail("Lavendar18614");
				registeredUser.getPrimaryAddress().setCountry("beta");
				registeredUser.getPrimaryAddress().setState("epsilon");

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

			registeredUser.setFirstName("Eric");
			registeredUser.setLastName("beta");
			registeredUser.setDob(dateFormat.parse("2007.10.24 07:59:24 EDT"));
			registeredUser.setAge(3873);
			registeredUser.setGender("pi");
			registeredUser.setImage("beta");
			registeredUser.getUserAccount().setUsername("Wilson89167");
			registeredUser.getUserAccount().setPassword("Eric");
			registeredUser.getUserAccount().setEnabled(true);
			registeredUser.getPrimaryAddress().setStreetAddress("Mark");
			registeredUser.getPrimaryAddress().setCity("gamma");
			registeredUser.getPrimaryAddress().setZip("Eric");
			registeredUser.getPrimaryAddress().setEmail("Wilson11925");
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
