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

			firmAdministrator.setFirstName("John");
			firmAdministrator.setLastName("Mark");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.06 00:33:55 EDT"));
			firmAdministrator.setEmail("Malissa41246");

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
			firmAdministrator.setLastName("epsilon");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.08.06 17:58:20 EDT"));
			firmAdministrator.setEmail("zeta82995");

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

			firmAdministrator.setFirstName("beta");
			firmAdministrator.setLastName("Eric");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.18 04:33:55 EDT"));
			firmAdministrator.setEmail("pi39864");

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
			firmAdministrator.setLastName("Eric");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.23 21:47:15 EDT"));
			firmAdministrator.setEmail("John18931");

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

			firmAdministrator.setFirstName("alpha");
			firmAdministrator.setLastName("Wilson");
			firmAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.18 08:17:15 EDT"));
			firmAdministrator.setEmail("Mark89776");

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
