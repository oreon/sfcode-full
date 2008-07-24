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

			firmInstance.setFirmName("Eric");
			firmInstance
					.setFirmType(com.cc.civlit.domain.FirmType.GOVERNMENT_REGULAORY);
			firmInstance.getContactDetails().setAddress1("epsilon");
			firmInstance.getContactDetails().setAddress2("Eric");
			firmInstance.getContactDetails().setCity("Mark");
			firmInstance.getContactDetails().setState("epsilon");
			firmInstance.getContactDetails().setCountry("gamma");
			firmInstance.getContactDetails().setPostalCode("theta");
			firmInstance.getContactDetails().setPhone("delta");
			firmInstance.getContactDetails().setFax("epsilon");
			firmInstance.getContactDetails().setEmail("Mark20326");

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

				firm.setFirmName("epsilon");
				firm
						.setFirmType(com.cc.civlit.domain.FirmType.GOVERNMENT_REGULAORY);
				firm.getContactDetails().setAddress1("Wilson");
				firm.getContactDetails().setAddress2("zeta");
				firm.getContactDetails().setCity("epsilon");
				firm.getContactDetails().setState("pi");
				firm.getContactDetails().setCountry("gamma");
				firm.getContactDetails().setPostalCode("beta");
				firm.getContactDetails().setPhone("Lavendar");
				firm.getContactDetails().setFax("alpha");
				firm.getContactDetails().setEmail("Lavendar2557");

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

			firm.setFirmName("delta");
			firm.setFirmType(com.cc.civlit.domain.FirmType.LAW_FIRM);
			firm.getContactDetails().setAddress1("gamma");
			firm.getContactDetails().setAddress2("Eric");
			firm.getContactDetails().setCity("Eric");
			firm.getContactDetails().setState("epsilon");
			firm.getContactDetails().setCountry("pi");
			firm.getContactDetails().setPostalCode("pi");
			firm.getContactDetails().setPhone("alpha");
			firm.getContactDetails().setFax("zeta");
			firm.getContactDetails().setEmail("gamma31537");

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
