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

			examCreator.setFirstName("John");
			examCreator.setLastName("John");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.04.17 04:29:33 EDT"));
			examCreator.getUser().setUsername("epsilon49240");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("Wilson");
			examCreator.getContactDetails().setState("zeta");
			examCreator.getContactDetails().setCountry("Eric");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("theta");
			examCreator.getContactDetails().setEmail("theta19500");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("theta");
			examCreator.setLastName("Malissa");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.04.29 10:35:41 EDT"));
			examCreator.getUser().setUsername("Wilson30049");
			examCreator.getUser().setPassword("Lavendar");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("John");
			examCreator.getContactDetails().setState("John");
			examCreator.getContactDetails().setCountry("theta");
			examCreator.getContactDetails().setZip("beta");
			examCreator.getContactDetails().setPhone("zeta");
			examCreator.getContactDetails().setEmail("alpha3754");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("pi");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.04.15 15:55:08 EDT"));
			examCreator.getUser().setUsername("theta66879");
			examCreator.getUser().setPassword("pi");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("beta");
			examCreator.getContactDetails().setCity("Lavendar");
			examCreator.getContactDetails().setState("zeta");
			examCreator.getContactDetails().setCountry("Malissa");
			examCreator.getContactDetails().setZip("Eric");
			examCreator.getContactDetails().setPhone("epsilon");
			examCreator.getContactDetails().setEmail("Lavendar68967");

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
			examCreator.setLastName("delta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.04.13 08:48:28 EDT"));
			examCreator.getUser().setUsername("beta89231");
			examCreator.getUser().setPassword("Lavendar");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("John");
			examCreator.getContactDetails().setCity("zeta");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("zeta");
			examCreator.getContactDetails().setPhone("zeta");
			examCreator.getContactDetails().setEmail("Lavendar29243");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Wilson");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.06 02:35:08 EDT"));
			examCreator.getUser().setUsername("alpha84195");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("zeta");
			examCreator.getContactDetails().setCity("zeta");
			examCreator.getContactDetails().setState("Wilson");
			examCreator.getContactDetails().setCountry("Mark");
			examCreator.getContactDetails().setZip("epsilon");
			examCreator.getContactDetails().setPhone("gamma");
			examCreator.getContactDetails().setEmail("beta76023");

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
