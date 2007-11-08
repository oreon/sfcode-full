package com.oreon.olympics.domain.service;

import com.oreon.olympics.domain.Country;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class CountryDaoTest extends AbstractJpaTests {

	protected Country countryInstance = new Country();

	protected CountryService countryService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	protected TestDataFactory countryTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("countryTestDataFactory");

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

			countryInstance.setName("theta");

			countryService.save(countryInstance);
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
			Country country = new Country();

			try {

				country.setName("Eric");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			countryService.save(country);
			assertNotNull(country.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Country country = (Country) countryTestDataFactory.loadOneRecord();

			country.setName("Malissa");

			countryService.save(country);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(countryService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Country country = countryService.load(countryInstance.getId());
			assertNotNull(country.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Country> countrys = countryService
					.searchByExample(countryInstance);
			assertTrue(!countrys.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
