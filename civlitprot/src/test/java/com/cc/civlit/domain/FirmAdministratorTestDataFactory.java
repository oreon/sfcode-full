package com.cc.civlit.domain;

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

import com.cc.civlit.service.FirmAdministratorService;

@Transactional
public class FirmAdministratorTestDataFactory
		extends
			AbstractTestDataFactory<FirmAdministrator> {

	private List<FirmAdministrator> firmAdministrators = new ArrayList<FirmAdministrator>();

	private static final Logger logger = Logger
			.getLogger(FirmAdministratorTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	FirmAdministratorService firmAdministratorService;

	public FirmAdministratorService getFirmAdministratorService() {
		return firmAdministratorService;
	}

	public void setFirmAdministratorService(
			FirmAdministratorService firmAdministratorService) {
		this.firmAdministratorService = firmAdministratorService;
	}

	public void register(FirmAdministrator firmAdministrator) {
		firmAdministrators.add(firmAdministrator);
	}

	public FirmAdministrator createFirmAdministratorOne() {
		FirmAdministrator firmAdministrator = new FirmAdministrator();

		try {

			firmAdministrator.setFirstName("gamma");
			firmAdministrator.setLastName("Eric");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.01 19:54:55 EDT"));
			firmAdministrator.setEmail("theta31562");

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			firmAdministrator
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(firmAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firmAdministrator;
	}

	public FirmAdministrator createFirmAdministratorTwo() {
		FirmAdministrator firmAdministrator = new FirmAdministrator();

		try {

			firmAdministrator.setFirstName("gamma");
			firmAdministrator.setLastName("Eric");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.08.07 03:25:28 EDT"));
			firmAdministrator.setEmail("beta71194");

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			firmAdministrator
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(firmAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firmAdministrator;
	}

	public FirmAdministrator createFirmAdministratorThree() {
		FirmAdministrator firmAdministrator = new FirmAdministrator();

		try {

			firmAdministrator.setFirstName("zeta");
			firmAdministrator.setLastName("gamma");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.08.16 03:05:28 EDT"));
			firmAdministrator.setEmail("Wilson77089");

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			firmAdministrator
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(firmAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firmAdministrator;
	}

	public FirmAdministrator createFirmAdministratorFour() {
		FirmAdministrator firmAdministrator = new FirmAdministrator();

		try {

			firmAdministrator.setFirstName("Lavendar");
			firmAdministrator.setLastName("Malissa");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.08.09 15:09:53 EDT"));
			firmAdministrator.setEmail("epsilon64147");

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			firmAdministrator
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(firmAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firmAdministrator;
	}

	public FirmAdministrator createFirmAdministratorFive() {
		FirmAdministrator firmAdministrator = new FirmAdministrator();

		try {

			firmAdministrator.setFirstName("gamma");
			firmAdministrator.setLastName("Malissa");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.18 21:36:33 EDT"));
			firmAdministrator.setEmail("gamma93740");

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			firmAdministrator
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(firmAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firmAdministrator;
	}

	public FirmAdministrator loadOneRecord() {
		List<FirmAdministrator> firmAdministrators = firmAdministratorService
				.loadAll();

		if (firmAdministrators.isEmpty()) {
			persistAll();
			firmAdministrators = firmAdministratorService.loadAll();
		}

		return firmAdministrators.get(new Random().nextInt(firmAdministrators
				.size()));
	}

	public List<FirmAdministrator> getAllAsList() {

		if (firmAdministrators.isEmpty()) {

			createFirmAdministratorOne();
			createFirmAdministratorTwo();
			createFirmAdministratorThree();
			createFirmAdministratorFour();
			createFirmAdministratorFive();

		}

		return firmAdministrators;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (FirmAdministrator firmAdministrator : firmAdministrators) {
			try {
				firmAdministratorService.save(firmAdministrator);
			} catch (BusinessException be) {
				logger.warn(" FirmAdministrator "
						+ firmAdministrator.getDisplayName()
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
			FirmAdministrator firmAdministrator = createRandomFirmAdministrator();
			firmAdministratorService.save(firmAdministrator);
		}
	}

	public FirmAdministrator createRandomFirmAdministrator() {
		FirmAdministrator firmAdministrator = new FirmAdministrator();

		firmAdministrator.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		firmAdministrator.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		firmAdministrator
				.setDateOfBirth((java.util.Date) RandomValueGeneratorFactory
						.createInstance("Date"));
		firmAdministrator.setEmail((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("firmTestDataFactory");

		firmAdministrator
				.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
						.loadOneRecord());

		return firmAdministrator;
	}

}
