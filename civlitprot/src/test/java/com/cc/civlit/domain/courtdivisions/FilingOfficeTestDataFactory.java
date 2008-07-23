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

import com.cc.civlit.domain.service.FilingOfficeService;

@Transactional
public class FilingOfficeTestDataFactory
		extends
			AbstractTestDataFactory<FilingOffice> {

	private List<FilingOffice> filingOffices = new ArrayList<FilingOffice>();

	private static final Logger logger = Logger
			.getLogger(FilingOfficeTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	FilingOfficeService filingOfficeService;

	public FilingOfficeService getFilingOfficeService() {
		return filingOfficeService;
	}

	public void setFilingOfficeService(FilingOfficeService filingOfficeService) {
		this.filingOfficeService = filingOfficeService;
	}

	public void register(FilingOffice filingOffice) {
		filingOffices.add(filingOffice);
	}

	public FilingOffice createFilingOfficeOne() {
		FilingOffice filingOffice = new FilingOffice();

		try {

			filingOffice.setName("epsilon");

			TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("levelOfCourtTestDataFactory");

			filingOffice
					.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
							.loadOneRecord());

			register(filingOffice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return filingOffice;
	}

	public FilingOffice createFilingOfficeTwo() {
		FilingOffice filingOffice = new FilingOffice();

		try {

			filingOffice.setName("pi");

			TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("levelOfCourtTestDataFactory");

			filingOffice
					.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
							.loadOneRecord());

			register(filingOffice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return filingOffice;
	}

	public FilingOffice createFilingOfficeThree() {
		FilingOffice filingOffice = new FilingOffice();

		try {

			filingOffice.setName("theta");

			TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("levelOfCourtTestDataFactory");

			filingOffice
					.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
							.loadOneRecord());

			register(filingOffice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return filingOffice;
	}

	public FilingOffice createFilingOfficeFour() {
		FilingOffice filingOffice = new FilingOffice();

		try {

			filingOffice.setName("Lavendar");

			TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("levelOfCourtTestDataFactory");

			filingOffice
					.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
							.loadOneRecord());

			register(filingOffice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return filingOffice;
	}

	public FilingOffice createFilingOfficeFive() {
		FilingOffice filingOffice = new FilingOffice();

		try {

			filingOffice.setName("Mark");

			TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("levelOfCourtTestDataFactory");

			filingOffice
					.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
							.loadOneRecord());

			register(filingOffice);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return filingOffice;
	}

	public FilingOffice loadOneRecord() {
		List<FilingOffice> filingOffices = filingOfficeService.loadAll();

		if (filingOffices.isEmpty()) {
			persistAll();
			filingOffices = filingOfficeService.loadAll();
		}

		return filingOffices.get(new Random().nextInt(filingOffices.size()));
	}

	public List<FilingOffice> getAllAsList() {

		if (filingOffices.isEmpty()) {

			createFilingOfficeOne();
			createFilingOfficeTwo();
			createFilingOfficeThree();
			createFilingOfficeFour();
			createFilingOfficeFive();

		}

		return filingOffices;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (FilingOffice filingOffice : filingOffices) {
			try {
				filingOfficeService.save(filingOffice);
			} catch (BusinessException be) {
				logger.warn(" FilingOffice " + filingOffice.getDisplayName()
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
			FilingOffice filingOffice = createRandomFilingOffice();
			filingOfficeService.save(filingOffice);
		}
	}

	public FilingOffice createRandomFilingOffice() {
		FilingOffice filingOffice = new FilingOffice();

		filingOffice.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("levelOfCourtTestDataFactory");

		filingOffice
				.setLevelOfCourt((com.cc.civlit.domain.courtdivisions.LevelOfCourt) levelOfCourtTestDataFactory
						.loadOneRecord());

		return filingOffice;
	}

}
