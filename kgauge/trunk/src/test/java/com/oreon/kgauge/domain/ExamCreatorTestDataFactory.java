package com.oreon.kgauge.domain;

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

import com.oreon.kgauge.service.ExamCreatorService;

@Transactional
public class ExamCreatorTestDataFactory
		extends
			AbstractTestDataFactory<ExamCreator> {

	private List<ExamCreator> examCreators = new ArrayList<ExamCreator>();

	private static final Logger logger = Logger
			.getLogger(ExamCreatorTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	ExamCreatorService examCreatorService;

	public ExamCreatorService getExamCreatorService() {
		return examCreatorService;
	}

	public void setExamCreatorService(ExamCreatorService examCreatorService) {
		this.examCreatorService = examCreatorService;
	}

	public void register(ExamCreator examCreator) {
		examCreators.add(examCreator);
	}

	public ExamCreator createExamCreatorOne() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("beta");
			examCreator.setLastName("gamma");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.01 06:19:16 EST"));
			examCreator.getUser().setUserName("pi24608");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Eric");
			examCreator.getContactDetails().setCity("zeta");
			examCreator.getContactDetails().setState("Mark");
			examCreator.getContactDetails().setCountry("zeta");
			examCreator.getContactDetails().setZip("alpha");
			examCreator.getContactDetails().setPhone("theta");
			examCreator.getContactDetails().setEmail("alpha28112");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("John");
			examCreator.setLastName("beta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.24 01:17:38 EDT"));
			examCreator.getUser().setUserName("Wilson88564");
			examCreator.getUser().setPassword("pi");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("zeta");
			examCreator.getContactDetails().setCity("pi");
			examCreator.getContactDetails().setState("Eric");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("epsilon");
			examCreator.getContactDetails().setPhone("Mark");
			examCreator.getContactDetails().setEmail("zeta17175");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Eric");
			examCreator.setLastName("delta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.14 15:08:10 EST"));
			examCreator.getUser().setUserName("epsilon9386");
			examCreator.getUser().setPassword("alpha");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("beta");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("pi");
			examCreator.getContactDetails().setCountry("Wilson");
			examCreator.getContactDetails().setZip("Mark");
			examCreator.getContactDetails().setPhone("pi");
			examCreator.getContactDetails().setEmail("John47381");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("epsilon");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.27 07:25:56 EST"));
			examCreator.getUser().setUserName("zeta32178");
			examCreator.getUser().setPassword("Lavendar");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("gamma");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("theta");
			examCreator.getContactDetails().setZip("alpha");
			examCreator.getContactDetails().setPhone("gamma");
			examCreator.getContactDetails().setEmail("zeta98266");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Malissa");
			examCreator.setLastName("Malissa");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.08 14:57:38 EST"));
			examCreator.getUser().setUserName("Mark68881");
			examCreator.getUser().setPassword("Eric");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("gamma");
			examCreator.getContactDetails().setCity("Mark");
			examCreator.getContactDetails().setState("theta");
			examCreator.getContactDetails().setCountry("gamma");
			examCreator.getContactDetails().setZip("epsilon");
			examCreator.getContactDetails().setPhone("epsilon");
			examCreator.getContactDetails().setEmail("Eric22573");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator loadOneRecord() {
		List<ExamCreator> examCreators = examCreatorService.loadAll();

		if (examCreators.isEmpty()) {
			persistAll();
			examCreators = examCreatorService.loadAll();
		}

		return examCreators.get(new Random().nextInt(examCreators.size()));
	}

	public List<ExamCreator> getAllAsList() {

		if (examCreators.isEmpty()) {

			createExamCreatorOne();
			createExamCreatorTwo();
			createExamCreatorThree();
			createExamCreatorFour();
			createExamCreatorFive();

		}

		return examCreators;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (ExamCreator examCreator : examCreators) {
			try {
				examCreatorService.save(examCreator);
			} catch (BusinessException be) {
				logger.warn(" ExamCreator " + examCreator.getDisplayName()
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
			ExamCreator examCreator = createRandomExamCreator();
			examCreatorService.save(examCreator);
		}
	}

	public ExamCreator createRandomExamCreator() {
		ExamCreator examCreator = new ExamCreator();

		examCreator.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		examCreator.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		examCreator.setDateOfBirth((java.util.Date) RandomValueGeneratorFactory
				.createInstance("Date"));
		examCreator.getUser().setUserName(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("Boolean"));
		examCreator.getContactDetails().setStreetAddress(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setCity(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setState(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setCountry(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setZip(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setPhone(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setEmail(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		return examCreator;
	}

}
