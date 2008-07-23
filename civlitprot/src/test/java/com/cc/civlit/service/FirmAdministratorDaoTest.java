package com.cc.civlit.service;

import com.cc.civlit.domain.FirmAdministrator;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class FirmAdministratorDaoTest extends AbstractJpaTests {

	protected FirmAdministrator firmAdministratorInstance = new FirmAdministrator();

	protected FirmAdministratorService firmAdministratorService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setFirmAdministratorService(
			FirmAdministratorService firmAdministratorService) {
		this.firmAdministratorService = firmAdministratorService;
	}

	protected TestDataFactory firmAdministratorTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("firmAdministratorTestDataFactory");

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

			firmAdministratorInstance.setFirstName("Eric");
			firmAdministratorInstance.setLastName("alpha");
			firmAdministratorInstance.setDateOfBirth(dateFormat
					.parse("2008.07.24 11:17:18 EDT"));
			firmAdministratorInstance.setEmail("John2061");

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			firmAdministratorInstance
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			firmAdministratorService.save(firmAdministratorInstance);
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
			FirmAdministrator firmAdministrator = new FirmAdministrator();

			try {

				firmAdministrator.setFirstName("pi");
				firmAdministrator.setLastName("gamma");
				firmAdministrator.setDateOfBirth(dateFormat
						.parse("2008.08.06 19:45:39 EDT"));
				firmAdministrator.setEmail("pi36770");

				TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("firmTestDataFactory");

				firmAdministrator
						.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			firmAdministratorService.save(firmAdministrator);
			assertNotNull(firmAdministrator.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			FirmAdministrator firmAdministrator = (FirmAdministrator) firmAdministratorTestDataFactory
					.loadOneRecord();

			firmAdministrator.setFirstName("beta");
			firmAdministrator.setLastName("theta");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.07 13:52:52 EDT"));
			firmAdministrator.setEmail("delta99369");

			firmAdministratorService.save(firmAdministrator);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(firmAdministratorService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = firmAdministratorService.getCount();
			FirmAdministrator firmAdministrator = (FirmAdministrator) firmAdministratorTestDataFactory
					.loadOneRecord();
			firmAdministratorService.delete(firmAdministrator);
			newCount = firmAdministratorService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			FirmAdministrator firmAdministrator = firmAdministratorService
					.load(firmAdministratorInstance.getId());
			assertNotNull(firmAdministrator.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByEmail() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a FirmAdministrator with email ",
				firmAdministratorService.findByEmail(firmAdministratorInstance
						.getEmail()));
		//assertNull("Found a FirmAdministrator with email YYY", firmAdministratorService.findByEmail("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<FirmAdministrator> firmAdministrators = firmAdministratorService
					.searchByExample(firmAdministratorInstance);
			assertTrue(!firmAdministrators.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
