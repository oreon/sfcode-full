package com.cc.civlit.service;

import com.cc.civlit.domain.Firm;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class FirmDaoTest extends AbstractJpaTests {

	protected Firm firmInstance = new Firm();

	protected FirmService firmService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setFirmService(FirmService firmService) {
		this.firmService = firmService;
	}

	protected TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("firmTestDataFactory");

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

			firmInstance.setFirmName("Malissa");
			firmInstance
					.setFirmType(com.cc.civlit.domain.FirmType.SOLE_PRACTITONER);
			firmInstance.getContactDetails().setAddress1("zeta");
			firmInstance.getContactDetails().setAddress2("Eric");
			firmInstance.getContactDetails().setCity("Malissa");
			firmInstance.getContactDetails().setState("Mark");
			firmInstance.getContactDetails().setCountry("John");
			firmInstance.getContactDetails().setPostalCode("Mark");
			firmInstance.getContactDetails().setPhone("pi");
			firmInstance.getContactDetails().setFax("Eric");
			firmInstance.getContactDetails().setEmail("pi95624");

			firmService.save(firmInstance);
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
			Firm firm = new Firm();

			try {

				firm.setFirmName("gamma");
				firm.setFirmType(com.cc.civlit.domain.FirmType.LAW_FIRM);
				firm.getContactDetails().setAddress1("John");
				firm.getContactDetails().setAddress2("Mark");
				firm.getContactDetails().setCity("alpha");
				firm.getContactDetails().setState("epsilon");
				firm.getContactDetails().setCountry("epsilon");
				firm.getContactDetails().setPostalCode("epsilon");
				firm.getContactDetails().setPhone("epsilon");
				firm.getContactDetails().setFax("zeta");
				firm.getContactDetails().setEmail("delta16000");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			firmService.save(firm);
			assertNotNull(firm.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Firm firm = (Firm) firmTestDataFactory.loadOneRecord();

			firm.setFirmName("zeta");
			firm.setFirmType(com.cc.civlit.domain.FirmType.SOLE_PRACTITONER);
			firm.getContactDetails().setAddress1("theta");
			firm.getContactDetails().setAddress2("Lavendar");
			firm.getContactDetails().setCity("epsilon");
			firm.getContactDetails().setState("Lavendar");
			firm.getContactDetails().setCountry("beta");
			firm.getContactDetails().setPostalCode("Lavendar");
			firm.getContactDetails().setPhone("Malissa");
			firm.getContactDetails().setFax("gamma");
			firm.getContactDetails().setEmail("Malissa47282");

			firmService.save(firm);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(firmService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = firmService.getCount();
			Firm firm = (Firm) firmTestDataFactory.loadOneRecord();
			firmService.delete(firm);
			newCount = firmService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			Firm firm = firmService.load(firmInstance.getId());
			assertNotNull(firm.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByEmail() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Firm with email ", firmService
				.findByEmail(firmInstance.getContactDetails().getEmail()));
		//assertNull("Found a Firm with email YYY", firmService.findByEmail("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<Firm> firms = firmService.searchByExample(firmInstance);
			assertTrue(!firms.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
