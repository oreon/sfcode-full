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

			firmAdministrator.setFirstName("Eric");
			firmAdministrator.setLastName("epsilon");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.08.15 19:52:29 EDT"));
			firmAdministrator.setEmail("Mark75528");
			firmAdministrator.getUser().setPassword("zeta");
			firmAdministrator.getUser().setEnabled(true);
			firmAdministrator.getUser().setUsername("theta67460");

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

			firmAdministrator.setFirstName("beta");
			firmAdministrator.setLastName("John");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.09 08:36:55 EDT"));
			firmAdministrator.setEmail("Eric30238");
			firmAdministrator.getUser().setPassword("theta");
			firmAdministrator.getUser().setEnabled(true);
			firmAdministrator.getUser().setUsername("gamma26528");

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

			firmAdministrator.setFirstName("gamma");
			firmAdministrator.setLastName("Malissa");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.22 07:09:42 EDT"));
			firmAdministrator.setEmail("pi64769");
			firmAdministrator.getUser().setPassword("gamma");
			firmAdministrator.getUser().setEnabled(false);
			firmAdministrator.getUser().setUsername("zeta12512");

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

			firmAdministrator.setFirstName("theta");
			firmAdministrator.setLastName("Wilson");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.08.10 08:49:42 EDT"));
			firmAdministrator.setEmail("theta76557");
			firmAdministrator.getUser().setPassword("Eric");
			firmAdministrator.getUser().setEnabled(false);
			firmAdministrator.getUser().setUsername("Lavendar49470");

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

			firmAdministrator.setFirstName("epsilon");
			firmAdministrator.setLastName("Malissa");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.28 06:01:24 EDT"));
			firmAdministrator.setEmail("John20368");
			firmAdministrator.getUser().setPassword("pi");
			firmAdministrator.getUser().setEnabled(false);
			firmAdministrator.getUser().setUsername("delta76369");

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
		firmAdministrator.getUser().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firmAdministrator.getUser()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("boolean"));
		firmAdministrator.getUser().setUsername(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("firmTestDataFactory");

		firmAdministrator
				.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
						.loadOneRecord());

		return firmAdministrator;
	}

}
