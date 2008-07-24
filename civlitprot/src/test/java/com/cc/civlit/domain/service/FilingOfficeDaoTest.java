package com.cc.civlit.domain.service;

import com.cc.civlit.domain.courtdivisions.FilingOffice;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class FilingOfficeDaoTest extends AbstractJpaTests {

	protected FilingOffice filingOfficeInstance = new FilingOffice();

	protected FilingOfficeService filingOfficeService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setFilingOfficeService(FilingOfficeService filingOfficeService) {
		this.filingOfficeService = filingOfficeService;
	}

	protected TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("filingOfficeTestDataFactory");

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

			filingOfficeInstance.setName("Wilson");

			TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("levelOfCourtTestDataFactory");

			filingOfficeInstance
					.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
							.loadOneRecord());

			filingOfficeService.save(filingOfficeInstance);
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
			FilingOffice filingOffice = new FilingOffice();

			try {

				filingOffice.setName("alpha");

				TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("levelOfCourtTestDataFactory");

				filingOffice
						.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			filingOfficeService.save(filingOffice);
			assertNotNull(filingOffice.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			FilingOffice filingOffice = (FilingOffice) filingOfficeTestDataFactory
					.loadOneRecord();

			filingOffice.setName("alpha");

			filingOfficeService.save(filingOffice);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(filingOfficeService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = filingOfficeService.getCount();
			FilingOffice filingOffice = (FilingOffice) filingOfficeTestDataFactory
					.loadOneRecord();
			filingOfficeService.delete(filingOffice);
			newCount = filingOfficeService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			FilingOffice filingOffice = filingOfficeService
					.load(filingOfficeInstance.getId());
			assertNotNull(filingOffice.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<FilingOffice> filingOffices = filingOfficeService
					.searchByExample(filingOfficeInstance);
			assertTrue(!filingOffices.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
