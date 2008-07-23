package com.cc.civlit.domain.service;

import com.cc.civlit.domain.courtdivisions.Jurisdiction;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class JurisdictionDaoTest extends AbstractJpaTests {

	protected Jurisdiction jurisdictionInstance = new Jurisdiction();

	protected JurisdictionService jurisdictionService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setJurisdictionService(JurisdictionService jurisdictionService) {
		this.jurisdictionService = jurisdictionService;
	}

	protected TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("jurisdictionTestDataFactory");

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

			jurisdictionInstance.setName("Mark");

			jurisdictionService.save(jurisdictionInstance);
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
			Jurisdiction jurisdiction = new Jurisdiction();

			try {

				jurisdiction.setName("delta");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			jurisdictionService.save(jurisdiction);
			assertNotNull(jurisdiction.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Jurisdiction jurisdiction = (Jurisdiction) jurisdictionTestDataFactory
					.loadOneRecord();

			jurisdiction.setName("delta");

			jurisdictionService.save(jurisdiction);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(jurisdictionService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = jurisdictionService.getCount();
			Jurisdiction jurisdiction = (Jurisdiction) jurisdictionTestDataFactory
					.loadOneRecord();
			jurisdictionService.delete(jurisdiction);
			newCount = jurisdictionService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			Jurisdiction jurisdiction = jurisdictionService
					.load(jurisdictionInstance.getId());
			assertNotNull(jurisdiction.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Jurisdiction> jurisdictions = jurisdictionService
					.searchByExample(jurisdictionInstance);
			assertTrue(!jurisdictions.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
