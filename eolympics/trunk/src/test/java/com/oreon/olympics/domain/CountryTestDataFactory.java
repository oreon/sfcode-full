package com.oreon.olympics.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.olympics.domain.service.CountryService;

@Transactional
public class CountryTestDataFactory extends AbstractTestDataFactory<Country> {

	List<Country> countrys = new ArrayList<Country>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	CountryService countryService;

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	public void register(Country country) {
		countrys.add(country);
	}

	public Country createCountryOne() {
		Country country = new Country();

		try {

			country.setName("Eric");

			register(country);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return country;
	}

	public Country createCountryTwo() {
		Country country = new Country();

		try {

			country.setName("gamma");

			register(country);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return country;
	}

	public Country createCountryThree() {
		Country country = new Country();

		try {

			country.setName("Lavendar");

			register(country);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return country;
	}

	public Country createCountryFour() {
		Country country = new Country();

		try {

			country.setName("Eric");

			register(country);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return country;
	}

	public Country createCountryFive() {
		Country country = new Country();

		try {

			country.setName("gamma");

			register(country);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return country;
	}

	public Country loadOneRecord() {
		List<Country> countrys = countryService.loadAll();

		if (countrys.isEmpty()) {
			persistAll();
			countrys = countryService.loadAll();
		}

		return countrys.get(new Random().nextInt(countrys.size()));
	}

	public List<Country> getAllAsList() {

		if (countrys.isEmpty()) {
			createCountryOne();
			createCountryTwo();
			createCountryThree();
			createCountryFour();
			createCountryFive();

		}

		return countrys;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Country country : countrys) {
			countryService.save(country);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Country country = createRandomCountry();
			countryService.save(country);
		}
	}

	public Country createRandomCountry() {
		Country country = new Country();

		country.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return country;
	}

}
