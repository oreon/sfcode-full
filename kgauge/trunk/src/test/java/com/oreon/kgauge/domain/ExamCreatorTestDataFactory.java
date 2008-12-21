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

			examCreator.setFirstName("zeta");
			examCreator.setLastName("epsilon");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.09 11:44:36 EST"));
			examCreator.getUser().setUsername("John53154");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Eric");
			examCreator.getContactDetails().setCity("zeta");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("John");
			examCreator.getContactDetails().setZip("beta");
			examCreator.getContactDetails().setPhone("zeta");
			examCreator.getContactDetails().setEmail("Lavendar30732");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Wilson");
			examCreator.setLastName("zeta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.18 12:07:56 EST"));
			examCreator.getUser().setUsername("zeta47410");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Malissa");
			examCreator.getContactDetails().setCity("alpha");
			examCreator.getContactDetails().setState("delta");
			examCreator.getContactDetails().setCountry("John");
			examCreator.getContactDetails().setZip("gamma");
			examCreator.getContactDetails().setPhone("Eric");
			examCreator.getContactDetails().setEmail("beta92352");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Lavendar");
			examCreator.setLastName("gamma");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.14 22:37:23 EST"));
			examCreator.getUser().setUsername("Malissa51211");
			examCreator.getUser().setPassword("beta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Malissa");
			examCreator.getContactDetails().setCity("Wilson");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("Malissa");
			examCreator.getContactDetails().setZip("Wilson");
			examCreator.getContactDetails().setPhone("Mark");
			examCreator.getContactDetails().setEmail("theta79778");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Wilson");
			examCreator.setLastName("alpha");
			examCreator.setDateOfBirth(dateFormat
					.parse("2009.01.10 10:32:25 EST"));
			examCreator.getUser().setUsername("Mark15929");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("beta");
			examCreator.getContactDetails().setCity("Mark");
			examCreator.getContactDetails().setState("delta");
			examCreator.getContactDetails().setCountry("delta");
			examCreator.getContactDetails().setZip("Wilson");
			examCreator.getContactDetails().setPhone("pi");
			examCreator.getContactDetails().setEmail("theta10282");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("gamma");
			examCreator.setLastName("alpha");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.13 22:07:23 EST"));
			examCreator.getUser().setUsername("Lavendar59963");
			examCreator.getUser().setPassword("epsilon");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Wilson");
			examCreator.getContactDetails().setCity("Wilson");
			examCreator.getContactDetails().setState("delta");
			examCreator.getContactDetails().setCountry("pi");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("pi");
			examCreator.getContactDetails().setEmail("Wilson1443");

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
		examCreator.getUser().setUsername(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("boolean"));
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
