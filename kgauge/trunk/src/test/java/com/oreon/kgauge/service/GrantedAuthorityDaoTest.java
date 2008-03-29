package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.GrantedAuthority;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class GrantedAuthorityDaoTest extends AbstractJpaTests {

	protected GrantedAuthority grantedAuthorityInstance = new GrantedAuthority();

	protected GrantedAuthorityService grantedAuthorityService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setGrantedAuthorityService(
			GrantedAuthorityService grantedAuthorityService) {
		this.grantedAuthorityService = grantedAuthorityService;
	}

	protected TestDataFactory grantedAuthorityTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("grantedAuthorityTestDataFactory");

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

			grantedAuthorityInstance.setName("theta");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedAuthorityInstance
					.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
							.loadOneRecord());

			grantedAuthorityService.save(grantedAuthorityInstance);
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
			GrantedAuthority grantedAuthority = new GrantedAuthority();

			try {

				grantedAuthority.setName("Lavendar");

				TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("userTestDataFactory");

				grantedAuthority
						.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			grantedAuthorityService.save(grantedAuthority);
			assertNotNull(grantedAuthority.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthorityTestDataFactory
					.loadOneRecord();

			grantedAuthority.setName("zeta");

			grantedAuthorityService.save(grantedAuthority);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(grantedAuthorityService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = grantedAuthorityService.getCount();
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthorityTestDataFactory
					.loadOneRecord();
			grantedAuthorityService.delete(grantedAuthority);
			newCount = grantedAuthorityService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			GrantedAuthority grantedAuthority = grantedAuthorityService
					.load(grantedAuthorityInstance.getId());
			assertNotNull(grantedAuthority.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<GrantedAuthority> grantedAuthoritys = grantedAuthorityService
					.searchByExample(grantedAuthorityInstance);
			assertTrue(!grantedAuthoritys.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
