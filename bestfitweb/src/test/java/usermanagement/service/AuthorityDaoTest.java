package usermanagement.service;

import usermanagement.Authority;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class AuthorityDaoTest extends AbstractJpaTests {

	protected Authority authorityInstance = new Authority();

	protected AuthorityService authorityService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	protected TestDataFactory authorityTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("authorityTestDataFactory");

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

			authorityInstance.setAuthority("beta");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authorityInstance.setUser((usermanagement.User) userTestDataFactory
					.loadOneRecord());

			authorityService.save(authorityInstance);
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
			Authority authority = new Authority();

			try {

				authority.setAuthority("pi");

				TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("userTestDataFactory");

				authority.setUser((usermanagement.User) userTestDataFactory
						.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			authorityService.save(authority);
			assertNotNull(authority.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Authority authority = (Authority) authorityTestDataFactory
					.loadOneRecord();

			authority.setAuthority("Lavendar");

			authorityService.save(authority);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(authorityService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Authority authority = authorityService.load(authorityInstance
					.getId());
			assertNotNull(authority.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Authority> authoritys = authorityService
					.searchByExample(authorityInstance);
			assertTrue(!authoritys.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
