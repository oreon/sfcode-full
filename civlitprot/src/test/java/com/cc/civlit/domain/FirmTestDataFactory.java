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

			firm.setFirmName("beta");
			firm.setFirmType(com.cc.civlit.domain.FirmType.LAW_FIRM);
			firm.getContactDetails().setAddress1("epsilon");
			firm.getContactDetails().setAddress2("pi");
			firm.getContactDetails().setCity("Malissa");
			firm.getContactDetails().setState("alpha");
			firm.getContactDetails().setCountry("Eric");
			firm.getContactDetails().setPostalCode("alpha");
			firm.getContactDetails().setPhone("delta");
			firm.getContactDetails().setFax("Mark");
			firm.getContactDetails().setEmail("Eric40875");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm createFirmTwo() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("beta");
			firm.setFirmType(com.cc.civlit.domain.FirmType.LAW_FIRM);
			firm.getContactDetails().setAddress1("epsilon");
			firm.getContactDetails().setAddress2("gamma");
			firm.getContactDetails().setCity("Mark");
			firm.getContactDetails().setState("delta");
			firm.getContactDetails().setCountry("zeta");
			firm.getContactDetails().setPostalCode("beta");
			firm.getContactDetails().setPhone("Malissa");
			firm.getContactDetails().setFax("delta");
			firm.getContactDetails().setEmail("epsilon46792");

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
			firm.getContactDetails().setAddress1("Lavendar");
			firm.getContactDetails().setAddress2("delta");
			firm.getContactDetails().setCity("gamma");
			firm.getContactDetails().setState("gamma");
			firm.getContactDetails().setCountry("zeta");
			firm.getContactDetails().setPostalCode("Malissa");
			firm.getContactDetails().setPhone("delta");
			firm.getContactDetails().setFax("Wilson");
			firm.getContactDetails().setEmail("delta42176");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm createFirmFour() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("Lavendar");
			firm
					.setFirmType(com.cc.civlit.domain.FirmType.GOVERNMENT_REGULAORY);
			firm.getContactDetails().setAddress1("theta");
			firm.getContactDetails().setAddress2("delta");
			firm.getContactDetails().setCity("delta");
			firm.getContactDetails().setState("gamma");
			firm.getContactDetails().setCountry("Lavendar");
			firm.getContactDetails().setPostalCode("theta");
			firm.getContactDetails().setPhone("epsilon");
			firm.getContactDetails().setFax("gamma");
			firm.getContactDetails().setEmail("Eric73438");

			register(firm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return firm;
	}

	public Firm createFirmFive() {
		Firm firm = new Firm();

		try {

			firm.setFirmName("beta");
			firm.setFirmType(com.cc.civlit.domain.FirmType.LAW_FIRM);
			firm.getContactDetails().setAddress1("Malissa");
			firm.getContactDetails().setAddress2("gamma");
			firm.getContactDetails().setCity("epsilon");
			firm.getContactDetails().setState("Eric");
			firm.getContactDetails().setCountry("Lavendar");
			firm.getContactDetails().setPostalCode("epsilon");
			firm.getContactDetails().setPhone("Wilson");
			firm.getContactDetails().setFax("Mark");
			firm.getContactDetails().setEmail("theta64063");

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
