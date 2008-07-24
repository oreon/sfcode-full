package com.cc.civlit.domain.courtdivisions;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.cc.civlit.domain.service.JurisdictionService;

@Transactional
public class JurisdictionTestDataFactory
		extends
			AbstractTestDataFactory<Jurisdiction> {

	private List<Jurisdiction> jurisdictions = new ArrayList<Jurisdiction>();

	private static final Logger logger = Logger
			.getLogger(JurisdictionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	JurisdictionService jurisdictionService;

	public JurisdictionService getJurisdictionService() {
		return jurisdictionService;
	}

	public void setJurisdictionService(JurisdictionService jurisdictionService) {
		this.jurisdictionService = jurisdictionService;
	}

	public void register(Jurisdiction jurisdiction) {
		jurisdictions.add(jurisdiction);
	}

	public Jurisdiction createJurisdictionOne() {
		Jurisdiction jurisdiction = new Jurisdiction();

		try {

			jurisdiction.setName("Eric");

			register(jurisdiction);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jurisdiction;
	}

	public Jurisdiction createJurisdictionTwo() {
		Jurisdiction jurisdiction = new Jurisdiction();

		try {

			jurisdiction.setName("Wilson");

			register(jurisdiction);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jurisdiction;
	}

	public Jurisdiction createJurisdictionThree() {
		Jurisdiction jurisdiction = new Jurisdiction();

		try {

			jurisdiction.setName("Lavendar");

			register(jurisdiction);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jurisdiction;
	}

	public Jurisdiction createJurisdictionFour() {
		Jurisdiction jurisdiction = new Jurisdiction();

		try {

			jurisdiction.setName("Malissa");

			register(jurisdiction);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jurisdiction;
	}

	public Jurisdiction createJurisdictionFive() {
		Jurisdiction jurisdiction = new Jurisdiction();

		try {

			jurisdiction.setName("beta");

			register(jurisdiction);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jurisdiction;
	}

	public Jurisdiction loadOneRecord() {
		List<Jurisdiction> jurisdictions = jurisdictionService.loadAll();

		if (jurisdictions.isEmpty()) {
			persistAll();
			jurisdictions = jurisdictionService.loadAll();
		}

		return jurisdictions.get(new Random().nextInt(jurisdictions.size()));
	}

	public List<Jurisdiction> getAllAsList() {

		if (jurisdictions.isEmpty()) {

			createJurisdictionOne();
			createJurisdictionTwo();
			createJurisdictionThree();
			createJurisdictionFour();
			createJurisdictionFive();

		}

		return jurisdictions;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Jurisdiction jurisdiction : jurisdictions) {
			try {
				jurisdictionService.save(jurisdiction);
			} catch (BusinessException be) {
				logger.warn(" Jurisdiction " + jurisdiction.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Jurisdiction jurisdiction = createRandomJurisdiction();
			jurisdictionService.save(jurisdiction);
		}
	}

	public Jurisdiction createRandomJurisdiction() {
		Jurisdiction jurisdiction = new Jurisdiction();

		jurisdiction.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return jurisdiction;
	}

}
