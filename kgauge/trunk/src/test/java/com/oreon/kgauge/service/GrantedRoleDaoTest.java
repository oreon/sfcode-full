package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.GrantedRole;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class GrantedRoleDaoTest extends AbstractJpaTests {

	protected GrantedRole grantedRoleInstance = new GrantedRole();

	protected GrantedRoleService grantedRoleService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setGrantedRoleService(GrantedRoleService grantedRoleService) {
		this.grantedRoleService = grantedRoleService;
	}

	protected TestDataFactory grantedRoleTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("grantedRoleTestDataFactory");

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

			grantedRoleInstance.setName("gamma");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedRoleInstance
					.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
							.loadOneRecord());

			grantedRoleService.save(grantedRoleInstance);
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
			GrantedRole grantedRole = new GrantedRole();

			try {

				grantedRole.setName("Mark");

				TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("userTestDataFactory");

				grantedRole
						.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			grantedRoleService.save(grantedRole);
			assertNotNull(grantedRole.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			GrantedRole grantedRole = (GrantedRole) grantedRoleTestDataFactory
					.loadOneRecord();

			grantedRole.setName("Lavendar");

			grantedRoleService.save(grantedRole);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(grantedRoleService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=grantedRoleService.getCount();
			GrantedRole grantedRole = (GrantedRole)grantedRoleTestDataFactory.loadOneRecord();					
			grantedRoleService.delete(grantedRole);
			newCount=grantedRoleService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			GrantedRole grantedRole = grantedRoleService
					.load(grantedRoleInstance.getId());
			assertNotNull(grantedRole.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<GrantedRole> grantedRoles = grantedRoleService
					.searchByExample(grantedRoleInstance);
			assertTrue(!grantedRoles.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
