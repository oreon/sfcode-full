package com.oreon.cerebrum.drugs;

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

import com.oreon.cerebrum.service.DrugCodeService;

@Transactional
public class DrugCodeTestDataFactory extends AbstractTestDataFactory<DrugCode> {

	private List<DrugCode> drugCodes = new ArrayList<DrugCode>();

	private static final Logger logger = Logger
			.getLogger(DrugCodeTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	DrugCodeService drugCodeService;

	public DrugCodeService getDrugCodeService() {
		return drugCodeService;
	}

	public void setDrugCodeService(DrugCodeService drugCodeService) {
		this.drugCodeService = drugCodeService;
	}

	public void register(DrugCode drugCode) {
		drugCodes.add(drugCode);
	}

	public DrugCode createDrugCodeOne() {
		DrugCode drugCode = new DrugCode();

		try {

			drugCode.setCode("beta86175");

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			drugCode
					.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
							.loadOneRecord());

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			drugCode
					.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
							.loadOneRecord());

			register(drugCode);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drugCode;
	}

	public DrugCode createDrugCodeTwo() {
		DrugCode drugCode = new DrugCode();

		try {

			drugCode.setCode("Mark87512");

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			drugCode
					.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
							.loadOneRecord());

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			drugCode
					.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
							.loadOneRecord());

			register(drugCode);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drugCode;
	}

	public DrugCode createDrugCodeThree() {
		DrugCode drugCode = new DrugCode();

		try {

			drugCode.setCode("Eric714");

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			drugCode
					.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
							.loadOneRecord());

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			drugCode
					.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
							.loadOneRecord());

			register(drugCode);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drugCode;
	}

	public DrugCode createDrugCodeFour() {
		DrugCode drugCode = new DrugCode();

		try {

			drugCode.setCode("beta53987");

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			drugCode
					.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
							.loadOneRecord());

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			drugCode
					.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
							.loadOneRecord());

			register(drugCode);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drugCode;
	}

	public DrugCode createDrugCodeFive() {
		DrugCode drugCode = new DrugCode();

		try {

			drugCode.setCode("Eric20563");

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			drugCode
					.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
							.loadOneRecord());

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			drugCode
					.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
							.loadOneRecord());

			register(drugCode);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drugCode;
	}

	public DrugCode loadOneRecord() {
		List<DrugCode> drugCodes = drugCodeService.loadAll();

		if (drugCodes.isEmpty()) {
			persistAll();
			drugCodes = drugCodeService.loadAll();
		}

		return drugCodes.get(new Random().nextInt(drugCodes.size()));
	}

	public List<DrugCode> getAllAsList() {

		if (drugCodes.isEmpty()) {

			createDrugCodeOne();
			createDrugCodeTwo();
			createDrugCodeThree();
			createDrugCodeFour();
			createDrugCodeFive();

		}

		return drugCodes;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (DrugCode drugCode : drugCodes) {
			try {
				drugCodeService.save(drugCode);
			} catch (BusinessException be) {
				logger.warn(" DrugCode " + drugCode.getDisplayName()
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
			DrugCode drugCode = createRandomDrugCode();
			drugCodeService.save(drugCode);
		}
	}

	public DrugCode createRandomDrugCode() {
		DrugCode drugCode = new DrugCode();

		drugCode.setCode((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("drugTestDataFactory");

		drugCode.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
				.loadOneRecord());

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		drugCode
				.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
						.loadOneRecord());

		return drugCode;
	}

}
