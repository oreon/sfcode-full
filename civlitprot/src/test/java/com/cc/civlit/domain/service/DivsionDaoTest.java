package com.cc.civlit.domain.service;

import com.cc.civlit.domain.courtdivisions.Divsion;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class DivsionDaoTest extends AbstractJpaTests {

	protected Divsion divsionInstance = new Divsion();

	protected DivsionService divsionService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setDivsionService(DivsionService divsionService) {
		this.divsionService = divsionService;
	}

	protected TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("divsionTestDataFactory");

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

			divsionInstance.setName("Wilson");

			TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("filingOfficeTestDataFactory");

			divsionInstance
					.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
							.loadOneRecord());

			divsionService.save(divsionInstance);
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
			Divsion divsion = new Divsion();

			try {

				divsion.setName("Mark");

				TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("filingOfficeTestDataFactory");

				divsion
						.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			divsionService.save(divsion);
			assertNotNull(divsion.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Divsion divsion = (Divsion) divsionTestDataFactory.loadOneRecord();

			divsion.setName("epsilon");

			divsionService.save(divsion);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(divsionService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = divsionService.getCount();
			Divsion divsion = (Divsion) divsionTestDataFactory.loadOneRecord();
			divsionService.delete(divsion);
			newCount = divsionService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			Divsion divsion = divsionService.load(divsionInstance.getId());
			assertNotNull(divsion.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Divsion> divsions = divsionService
					.searchByExample(divsionInstance);
			assertTrue(!divsions.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
