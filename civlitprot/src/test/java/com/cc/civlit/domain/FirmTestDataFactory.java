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

import com.cc.civlit.service.FirmService;

@Transactional
public class FirmTestDataFactory extends AbstractTestDataFactory<Firm> {

	private List<Firm> firms = new ArrayList<Firm>();

	private static final Logger logger = Logger
			.getLogger(FirmTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	FirmService firmService;

	public FirmService getFirmService() {
		return firmService;
	}

	public void setFirmService(FirmService firmService) {
		this.firmService = firmService;
	}

	public void register(Firm firm) {
		firms.add(firm);
	}

	public Firm createFirmOne() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("Eric");
			firm.setFirmType(com.cc.civlit.domain.FirmType.PARALEGAL);
			firm.getContactDetails().setAddress1("pi");
			firm.getContactDetails().setAddress2("delta");
			firm.getContactDetails().setCity("zeta");
			firm.getContactDetails().setState("gamma");
			firm.getContactDetails().setCountry("gamma");
			firm.getContactDetails().setPostalCode("gamma");
			firm.getContactDetails().setPhone("delta");
			firm.getContactDetails().setFax("Malissa");
			firm.getContactDetails().setEmail("pi38692");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm createFirmTwo() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("gamma");
			firm.setFirmType(com.cc.civlit.domain.FirmType.PARALEGAL);
			firm.getContactDetails().setAddress1("Lavendar");
			firm.getContactDetails().setAddress2("John");
			firm.getContactDetails().setCity("Malissa");
			firm.getContactDetails().setState("zeta");
			firm.getContactDetails().setCountry("delta");
			firm.getContactDetails().setPostalCode("Wilson");
			firm.getContactDetails().setPhone("alpha");
			firm.getContactDetails().setFax("theta");
			firm.getContactDetails().setEmail("pi60054");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm createFirmThree() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("John");
			firm.setFirmType(com.cc.civlit.domain.FirmType.CORPORATE_COUNSEL);
			firm.getContactDetails().setAddress1("theta");
			firm.getContactDetails().setAddress2("beta");
			firm.getContactDetails().setCity("alpha");
			firm.getContactDetails().setState("Mark");
			firm.getContactDetails().setCountry("beta");
			firm.getContactDetails().setPostalCode("beta");
			firm.getContactDetails().setPhone("delta");
			firm.getContactDetails().setFax("alpha");
			firm.getContactDetails().setEmail("epsilon55414");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm createFirmFour() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("theta");
			firm
					.setFirmType(com.cc.civlit.domain.FirmType.GOVERNMENT_REGULAORY);
			firm.getContactDetails().setAddress1("pi");
			firm.getContactDetails().setAddress2("Eric");
			firm.getContactDetails().setCity("John");
			firm.getContactDetails().setState("zeta");
			firm.getContactDetails().setCountry("Malissa");
			firm.getContactDetails().setPostalCode("beta");
			firm.getContactDetails().setPhone("John");
			firm.getContactDetails().setFax("John");
			firm.getContactDetails().setEmail("beta23796");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm createFirmFive() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("zeta");
			firm.setFirmType(com.cc.civlit.domain.FirmType.LAW_FIRM);
			firm.getContactDetails().setAddress1("Wilson");
			firm.getContactDetails().setAddress2("zeta");
			firm.getContactDetails().setCity("alpha");
			firm.getContactDetails().setState("theta");
			firm.getContactDetails().setCountry("zeta");
			firm.getContactDetails().setPostalCode("pi");
			firm.getContactDetails().setPhone("delta");
			firm.getContactDetails().setFax("delta");
			firm.getContactDetails().setEmail("Lavendar57417");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm loadOneRecord() {
		List<Firm> firms = firmService.loadAll();

		if (firms.isEmpty()) {
			persistAll();
			firms = firmService.loadAll();
		}

		return firms.get(new Random().nextInt(firms.size()));
	}

	public List<Firm> getAllAsList() {

		if (firms.isEmpty()) {

			createFirmOne();
			createFirmTwo();
			createFirmThree();
			createFirmFour();
			createFirmFive();

		}

		return firms;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Firm firm : firms) {
			try {
				firmService.save(firm);
			} catch (BusinessException be) {
				logger.warn(" Firm " + firm.getDisplayName()
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
			Firm firm = createRandomFirm();
			firmService.save(firm);
		}
	}

	public Firm createRandomFirm() {
		Firm firm = new Firm();

		firm.setFirmName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		firm.setFirmType((FirmType) RandomValueGeneratorFactory
				.createInstance("FirmType"));
		firm.getContactDetails().setAddress1(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setAddress2(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setCity(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setState(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setCountry(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setPostalCode(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setPhone(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setFax(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		firm.getContactDetails().setEmail(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		return firm;
	}

}
